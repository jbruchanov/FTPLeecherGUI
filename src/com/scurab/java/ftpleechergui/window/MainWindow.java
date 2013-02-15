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
        this.$$$loadButtonText$$$(mOpenConnection, ResourceBundle.getBundle("Labels").getString("OpenConnection"));
        mOpenConnection.setToolTipText(ResourceBundle.getBundle("Labels").getString("OpenConnection"));
        mHeaderBar.add(mOpenConnection);
        mDisconnect = new JButton();
        this.$$$loadButtonText$$$(mDisconnect, ResourceBundle.getBundle("Labels").getString("Disconnect"));
        mDisconnect.setToolTipText(ResourceBundle.getBundle("Labels").getString("Disconnect"));
        mHeaderBar.add(mDisconnect);
        mDownload = new JButton();
        this.$$$loadButtonText$$$(mDownload, ResourceBundle.getBundle("Labels").getString("Download"));
        mHeaderBar.add(mDownload);
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mContentPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSplitPane splitPane1 = new JSplitPane();
        splitPane1.setDividerLocation(300);
        splitPane1.setDividerSize(5);
        splitPane1.setDoubleBuffered(false);
        splitPane1.setOrientation(0);
        mContentPanel.add(splitPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JSplitPane splitPane2 = new JSplitPane();
        splitPane2.setDividerLocation(453);
        splitPane2.setDividerSize(5);
        splitPane1.setLeftComponent(splitPane2);
        final JScrollPane scrollPane1 = new JScrollPane();
        splitPane2.setLeftComponent(scrollPane1);
        mLocalStorage = new JTable();
        scrollPane1.setViewportView(mLocalStorage);
        final JScrollPane scrollPane2 = new JScrollPane();
        splitPane2.setRightComponent(scrollPane2);
        mFtpStorage = new JTable();
        scrollPane2.setViewportView(mFtpStorage);
        final JScrollPane scrollPane3 = new JScrollPane();
        splitPane1.setRightComponent(scrollPane3);
        mQueue = new JTable();
        scrollPane3.setViewportView(mQueue);
        mStatusBar = new JToolBar();
        mStatusBar.setFloatable(false);
        mStatusBar.setFocusable(false);
        mContentPanel.add(mStatusBar, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
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
        final JToolBar.Separator toolBar$Separator1 = new JToolBar.Separator();
        mStatusBar.add(toolBar$Separator1);
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
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
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
            component.setMnemonic(mnemonic);
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
