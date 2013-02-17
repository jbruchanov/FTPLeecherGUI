package com.scurab.java.ftpleechergui.controller;

import com.scurab.java.ftpleecher.FTPConnection;
import com.scurab.java.ftpleecher.FTPFactory;
import com.scurab.java.ftpleecher.FTPLeechMaster;
import com.scurab.java.ftpleecher.FatalFTPException;
import com.scurab.java.ftpleechergui.TextUtils;
import com.scurab.java.ftpleechergui.model.Settings;
import com.scurab.java.ftpleechergui.window.MainWindow;
import com.scurab.java.ftpleechergui.window.OpenConnectionDialog;
import com.scurab.java.ftpleechergui.window.SettingsDialog;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class MainWindowController extends BaseController {

    public static final int DELAY = 2000;

    private MainWindow mWindow;

    private LocalStorageController mStorageController;

    private FTPController mFtpController;

    private DownloadController mDownloadController;

    private FTPClient mFtpClient;

    private FTPLeechMaster mMaster;

    private TrayIcon mTrayIcon;

    public MainWindowController(MainWindow window) {
        mWindow = window;
        try {
            mWindow.setIconImage(ImageIO.read(getClass().getResourceAsStream("/assets/appicon.png")));
            mWindow.setTitle(getResourceLabel("AppTitle"));
            mWindow.setMinimumSize(new Dimension(250,150));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        bind();
        mWindow.setVisible(true);
        initSysTray();
    }

    private void bind() {
        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAction(e.getSource(), e.getActionCommand());
            }
        };
        mWindow.getOpenConnection().addActionListener(action);
        mWindow.getDisconnect().addActionListener(action);
        mWindow.getDownload().addActionListener(action);
        mWindow.getSettings().addActionListener(action);
        mWindow.getReload().addActionListener(action);
        mWindow.getResume().addActionListener(action);
        mWindow.getPause().addActionListener(action);
        mWindow.getRestart().addActionListener(action);
        mWindow.getWeb().addActionListener(action);

        mWindow.getQueue().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                onQueueSelectionChange(mWindow.getQueue().getSelectedRows());
            }
        });

        mWindow.getFtpStorage().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                onFtpSelectionChange(mWindow.getFtpStorage().getSelectedRows());
            }
        });
    }

    private void initSysTray(){
        if(SystemTray.isSupported()){
            mWindow.addWindowStateListener(new WindowStateListener() {
                public void windowStateChanged(WindowEvent e) {
                    if (e.getNewState() == JFrame.ICONIFIED) {
                        try {
                            if(mTrayIcon == null){
                                mTrayIcon = new TrayIcon(ImageIO.read(getClass().getResourceAsStream("/assets/appicon.png")));mTrayIcon.setImageAutoSize(true);
                                mTrayIcon.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        mWindow.setVisible(true);
                                        mWindow.setState(JFrame.NORMAL);
                                        mWindow.toFront();
                                        mWindow.requestFocusInWindow();
                                        SystemTray.getSystemTray().remove(mTrayIcon);
                                    }
                                });
                            }
                            SystemTray.getSystemTray().add(mTrayIcon);
                            mWindow.setVisible(false);
                        } catch (AWTException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void onAction(Object source, String action) {
        if ("OpenConnection".equals(action)) {
           onOpeningConnection();
        } else if ("Disconnect".equals(action)) {
            onCloseConnection();
        } else if ("Download".equals(action)) {
            onDownload();
        } else if ("Settings".equals(action)){
            onSettings();
        } else if ("Refresh".equals(action)){
            onReload();
        }else if ("Pause".equals(action)){
            onPause();
        }else if ("Resume".equals(action)){
            onResume();
        }else if ("Restart".equals(action)){
            onRestart();
        }
        else if ("Web".equals(action)){
            onOpenWeb();
        }
    }

    private void onOpeningConnection() {
        //show open ftp connection dialog
        OpenConnectionDialog ocd = new OpenConnectionDialog(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenConnectionDialog o = (OpenConnectionDialog) e.getSource();
                if(onOpenConnection(o.getValues())){
                    o.setVisible(false);
                }
            }
        });
        ocd.setVisible(true);
    }

    private void onSettings() {
        SettingsDialog sd = new SettingsDialog(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsDialog d = (SettingsDialog) e.getSource();
                Settings s = d.getSettings();
                application().setSettings(s);
                mDownloadController.onSettingsChanged();
                mMaster.setWorkingThreads(s.threads);
            }
        });
        sd.setSettings(application().getSettings());
        sd.setVisible(true);
    }

    public void onDownload() {
        try {
            final FTPFile[] selection = mFtpController.getSelectedItems();
            if (selection.length == 0 || selection[0] == null) {
                throw new Exception(getResourceLabel("NoSelectedItem"));
            } else {
                final File to = mStorageController.getCurrentFolder();
                if (to == null) {
                    throw new Exception(getResourceLabel("SelectDestination"));
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            showProgress(true);
                            final String pwd = getFtpPwd();
                            for(FTPFile file : selection){
                                mDownloadController.onDownloadItem(pwd, file, to.getAbsolutePath());
                            }
                        } catch (Exception e) {
                            showError(e.getMessage());
                        }
                        showProgress(false);
                    }
                }).start();
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    /**
     * return ftp current folder
     *
     * @return
     * @throws IOException
     */
    private String getFtpPwd() throws IOException {
        String ftpPwd = null;

        StringBuilder pwd = new StringBuilder(mFtpClient.printWorkingDirectory());
        if (pwd.charAt(pwd.length() - 1) == '\"') {
            pwd.setLength(pwd.length() - 1);
        }
        if (pwd.charAt(0) == '\"') {
            ftpPwd = pwd.substring(1);
        }

        if (ftpPwd == null) {
            ftpPwd = pwd.toString();
        }
        return ftpPwd;
    }

    /**
     * Open new connection
     *
     * @param config
     */
    public boolean onOpenConnection(FTPConnection config) {
        try {
            if(TextUtils.isNullOrEmpty(config.server)){
                throw new Exception("Server is not defined!");
            }
            onCloseConnection();
            mFtpClient = FTPFactory.openFtpClient(config);
            mFtpController.setFTPClient(mFtpClient);
            mDownloadController.setConfig(config);
            return true;
        } catch (Exception e) {
            showError(e.getMessage());
            return false;
        }
    }

    public void onCloseConnection() {
        if (mFtpClient != null) {
            try {
                mFtpClient.disconnect();
                mFtpClient = null;
            } catch (IOException e) {
                e.printStackTrace();
                showStatusBarMessage(e.getMessage());
                //ignore it
            }
        }
        mFtpController.setFTPClient(null);
    }

    public void onCreate() {
        mStorageController = new LocalStorageController(mWindow.getLocalStorage());
        mFtpController = new FTPController(mWindow.getFtpStorage());
        mMaster = application().getMaster();
        mDownloadController = new DownloadController(mWindow.getQueue(), mMaster);
        Timer t = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRefreshStats();

            }
        });
        t.start();
    }

    public void onRefreshStats(){
        FTPLeechMaster.Statistics s = mMaster.getStatistics();
        mWindow.getETA().setText(TextUtils.convertTime(s.eta));
        mWindow.getGlobalSpeed().setText(TextUtils.getSpeedReadable(s.currentSpeed));
    }

    public void onReload() {
        mStorageController.reload();
        mFtpController.reload();
    }

    private void onPause() {
        try {
            mDownloadController.onPause(mWindow.getQueue().getSelectedRows());
        } catch (Exception e) {
            showMessageBox(e.getMessage(), 0);
        }
    }

    private void onResume() {
        try {
            mDownloadController.onResume(mWindow.getQueue().getSelectedRows());
        } catch (Exception e) {
            showMessageBox(e.getMessage(), 0);
        }
    }

    private void onRestart() {
        try {
            mDownloadController.onRestart(mWindow.getQueue().getSelectedRows());
        } catch (Exception e) {
            showMessageBox(e.getMessage(), 0);
        }
    }

    public void onQueueSelectionChange(int [] selectedRows){
        mWindow.setFtpButtonsEnabled(selectedRows.length > 0);
        mWindow.getRestart().setEnabled(selectedRows.length > 0);
    }

    private void onFtpSelectionChange(int[] selectedRows) {
        mWindow.getDownload().setEnabled(selectedRows.length > 0 && selectedRows[0] != 0);
    }

    private void onOpenWeb() {
        try {
            Desktop dt = Desktop.getDesktop();
            dt.browse(URI.create(getResourceLabel("WebURL")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStatusBarMessage(String s) {
        super.showStatusBarMessage(s);
    }

    @Override
    public void showStatusBarMessage(String s, int type) {
        mWindow.getStatusLabel().setText(s);
    }

    @Override
    public void showProgress(boolean value) {
        mWindow.getProgressBar().setVisible(value);
    }

    @Override
    public Component getView() {
        return mWindow;
    }
}
