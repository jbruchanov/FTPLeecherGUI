package com.scurab.java.ftpleechergui.window;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 12.2.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow extends JFrame {

    //region UI
    private JPanel mContentPanel;
    private JTable mQueue;
    private JTable mLocalStorage;
    private JTable mFtpStorage;
    private JToolBar mHeaderBar;
    private JToolBar mStatusBar;
    private JLabel mStatusLabel;
    private JProgressBar mProgressBar;
    private JButton mOpenConnection;
    private JButton mDisconnect;
    private JButton mDownload;
    private JLabel mGlobalSpeed;
    private JButton mSettings;
    private JButton mReload;
    private JButton mPause;
    private JButton mResume;
    private JButton mRestart;
    private JButton mWeb;

    //endregion UI

    public MainWindow() throws HeadlessException {
        setContentPane(mContentPanel);
    }

    //region UI getters
    public JTable getLocalStorage() {
        return mLocalStorage;
    }

    public JTable getQueue() {
        return mQueue;
    }

    public JTable getFtpStorage() {
        return mFtpStorage;
    }

    public JLabel getStatusLabel() {
        return mStatusLabel;
    }

    public JProgressBar getProgressBar() {
        return mProgressBar;
    }

    public JButton getOpenConnection() {
        return mOpenConnection;
    }

    public JButton getDisconnect() {
        return mDisconnect;
    }

    public JButton getDownload() {
        return mDownload;
    }

    public JLabel getGlobalSpeed() {
        return mGlobalSpeed;
    }

    public JButton getSettings() {
        return mSettings;
    }

    public JButton getReload() {
        return mReload;
    }

    public JButton getPause() {
        return mPause;
    }

    public JButton getResume() {
        return mResume;
    }

    public JButton getRestart() {
        return mRestart;
    }

    public void setFtpButtonsEnabled(boolean v) {
        mPause.setEnabled(v);
        mResume.setEnabled(v);
    }

    public JButton getWeb() {
        return mWeb;
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mContentPanel = new JPanel();
        mContentPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        mContentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null));
        mHeaderBar = new JToolBar();
        mHeaderBar.setFloatable(false);
        mContentPanel.add(mHeaderBar, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        mOpenConnection = new JButton();
        mOpenConnection.setActionCommand("OpenConnection");
        mOpenConnection.setIcon(new ImageIcon(getClass().getResource("/assets/connect.png")));
        mOpenConnection.setText("");
        mOpenConnection.setToolTipText(ResourceBundle.getBundle("Labels").getString("OpenConnection"));
        mHeaderBar.add(mOpenConnection);
        mDisconnect = new JButton();
        mDisconnect.setActionCommand("Disconnect");
        mDisconnect.setIcon(new ImageIcon(getClass().getResource("/assets/disconnect.png")));
        mDisconnect.setText("");
        mDisconnect.setToolTipText(ResourceBundle.getBundle("Labels").getString("Disconnect"));
        mHeaderBar.add(mDisconnect);
        mReload = new JButton();
        mReload.setActionCommand("Refresh");
        mReload.setIcon(new ImageIcon(getClass().getResource("/assets/refresh.png")));
        mReload.setText("");
        mReload.setToolTipText(ResourceBundle.getBundle("Labels").getString("Refresh"));
        mHeaderBar.add(mReload);
        final JToolBar.Separator toolBar$Separator1 = new JToolBar.Separator();
        mHeaderBar.add(toolBar$Separator1);
        mDownload = new JButton();
        mDownload.setActionCommand("Download");
        mDownload.setEnabled(false);
        mDownload.setIcon(new ImageIcon(getClass().getResource("/assets/download.png")));
        mDownload.setText("");
        mDownload.setToolTipText(ResourceBundle.getBundle("Labels").getString("Download"));
        mHeaderBar.add(mDownload);
        mPause = new JButton();
        mPause.setActionCommand("Pause");
        mPause.setEnabled(false);
        mPause.setIcon(new ImageIcon(getClass().getResource("/assets/pause.png")));
        mPause.setText("");
        mPause.setToolTipText(ResourceBundle.getBundle("Labels").getString("Pause"));
        mHeaderBar.add(mPause);
        mResume = new JButton();
        mResume.setActionCommand("Resume");
        mResume.setEnabled(false);
        mResume.setIcon(new ImageIcon(getClass().getResource("/assets/resume.png")));
        mResume.setInheritsPopupMenu(false);
        mResume.setText("");
        mResume.setToolTipText(ResourceBundle.getBundle("Labels").getString("Resume"));
        mHeaderBar.add(mResume);
        mRestart = new JButton();
        mRestart.setActionCommand("Restart");
        mRestart.setEnabled(false);
        mRestart.setIcon(new ImageIcon(getClass().getResource("/assets/restart.png")));
        mRestart.setText("");
        mRestart.setToolTipText(ResourceBundle.getBundle("Labels").getString("Restart"));
        mHeaderBar.add(mRestart);
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mHeaderBar.add(spacer1);
        mSettings = new JButton();
        mSettings.setActionCommand("Settings");
        mSettings.setIcon(new ImageIcon(getClass().getResource("/assets/settings.png")));
        mSettings.setText("");
        mSettings.setToolTipText(ResourceBundle.getBundle("Labels").getString("Settings"));
        mHeaderBar.add(mSettings);
        mWeb = new JButton();
        mWeb.setActionCommand("Web");
        mWeb.setIcon(new ImageIcon(getClass().getResource("/assets/web.png")));
        mWeb.setText("");
        mWeb.setToolTipText("Web");
        mWeb.putClientProperty("html.disable", Boolean.FALSE);
        mHeaderBar.add(mWeb);
        final JSplitPane splitPane1 = new JSplitPane();
        splitPane1.setDividerLocation(277);
        splitPane1.setDividerSize(5);
        splitPane1.setDoubleBuffered(false);
        splitPane1.setOrientation(0);
        mContentPanel.add(splitPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(705, 200), null, 0, false));
        final JSplitPane splitPane2 = new JSplitPane();
        splitPane2.setDividerLocation(453);
        splitPane2.setDividerSize(5);
        splitPane1.setLeftComponent(splitPane2);
        final JScrollPane scrollPane1 = new JScrollPane();
        splitPane2.setLeftComponent(scrollPane1);
        mLocalStorage = new JTable();
        mLocalStorage.setDoubleBuffered(true);
        scrollPane1.setViewportView(mLocalStorage);
        final JScrollPane scrollPane2 = new JScrollPane();
        splitPane2.setRightComponent(scrollPane2);
        mFtpStorage = new JTable();
        mFtpStorage.setDoubleBuffered(true);
        scrollPane2.setViewportView(mFtpStorage);
        final JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setToolTipText("");
        splitPane1.setRightComponent(scrollPane3);
        mQueue = new JTable();
        mQueue.setAutoCreateRowSorter(false);
        mQueue.setDoubleBuffered(true);
        scrollPane3.setViewportView(mQueue);
        mStatusBar = new JToolBar();
        mStatusBar.setFloatable(false);
        mStatusBar.setFocusable(false);
        mContentPanel.add(mStatusBar, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(705, 20), null, 0, false));
        mProgressBar = new JProgressBar();
        mProgressBar.setIndeterminate(true);
        mProgressBar.setMaximumSize(new Dimension(50, 12));
        mProgressBar.setVisible(false);
        mStatusBar.add(mProgressBar);
        mStatusLabel = new JLabel();
        mStatusLabel.setAlignmentX(0.5f);
        mStatusLabel.setText("");
        mStatusLabel.setToolTipText("Tooltip");
        mStatusBar.add(mStatusLabel);
        final JToolBar.Separator toolBar$Separator2 = new JToolBar.Separator();
        mStatusBar.add(toolBar$Separator2);
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mStatusBar.add(spacer2);
        final JLabel label1 = new JLabel();
        label1.setAlignmentX(0.0f);
        label1.setMinimumSize(new Dimension(30, 14));
        label1.setPreferredSize(new Dimension(50, 14));
        this.$$$loadLabelText$$$(label1, ResourceBundle.getBundle("Labels").getString("Speed"));
        mStatusBar.add(label1);
        mGlobalSpeed = new JLabel();
        mGlobalSpeed.setPreferredSize(new Dimension(60, 14));
        mGlobalSpeed.setText("");
        mStatusBar.add(mGlobalSpeed);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mContentPanel;
    }
}
