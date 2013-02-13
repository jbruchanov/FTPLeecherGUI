package com.scurab.java.ftpleechergui.controller;

import com.scurab.java.ftpleechergui.window.MainWindow;
import com.scurab.java.ftpleechergui.window.OpenConnectionDialog;
import org.apache.commons.net.ftp.FTPClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 12.2.13
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
public class MainWindowController extends BaseController {

    private MainWindow mWindow;

    private LocalStorageController mStorageController;

    private FTPController mFtpController;

    public MainWindowController(MainWindow window) {
        mWindow = window;
        try {
            mWindow.setIconImage(ImageIO.read(getClass().getResourceAsStream("/assets/appicon.png")));
            mWindow.setTitle("FTP Leecher");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        bind();
        mWindow.setVisible(true);
    }

    private void bind(){
        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               onAction(e.getSource(), e.getActionCommand());
            }
        };
        mWindow.getOpenConnection().addActionListener(action);
        mWindow.getDisconnect().addActionListener(action);
    }

    public void onAction(Object source, String action){
        if("OpenConnection".equals(action)){
            OpenConnectionDialog ocd = new OpenConnectionDialog(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        OpenConnectionDialog o = (OpenConnectionDialog) e.getSource();
                        String[] values = o.getValues();
                        FTPClient fc = new FTPClient();
                        fc.connect(values[0], Integer.parseInt(values[1]));
                        fc.login(values[2], values[3]);
                        mFtpController.setFTPClient(fc);
                    } catch (IOException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            });
            ocd.setVisible(true);
        }else if("Disconnect".equals(action)){
            mFtpController.setFTPClient(null);
        }
    }

    public void onCreate() {
        mStorageController = new LocalStorageController(mWindow.getLocalStorage());
        mFtpController = new FTPController(mWindow.getFtpStorage());
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
}
