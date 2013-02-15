package com.scurab.java.ftpleechergui;

import com.scurab.java.ftpleecher.FTPLeechMaster;
import com.scurab.java.ftpleechergui.controller.ApplicationController;

import java.util.ResourceBundle;

/**
 * Base application class
 */
public class Application {

    private static Application sSelf;

    private ResourceBundle mLables;

    private ApplicationController mAppController;

    private FTPLeechMaster mMaster;

    public static void main(String[] args) {
        sSelf = new Application();
        sSelf.start();
    }

    public Application() {
        if (sSelf != null) {
            throw new IllegalStateException("App object is already created");
        }
    }

    public static Application getInstance() {
        return sSelf;
    }

    /**
     * ResourceBundle for lables
     *
     * @return
     */
    public static ResourceBundle getLabels() {
        return sSelf.mLables;
    }

    /**
     * Start application
     */
    public void start() {
        onLoadResources();
        mMaster = new FTPLeechMaster();

        mAppController = new ApplicationController();
        mAppController.start();
    }

    protected void onLoadResources() {
        mLables = ResourceBundle.getBundle("Labels");
    }

    /**
     * Shows message in mainwindows status bar
     *
     * @param msg
     * @param type
     */
    public void showStatusBarMessage(String msg, int type) {
        mAppController.showStatusBarMessage(msg, type);
    }

    /**
     * Set visibility for indtereminate progress bar in status bar
     *
     * @param value
     */
    public void showProgress(boolean value) {
        mAppController.showProgress(value);
    }

    /**
     * returns singleton of {@link FTPLeechMaster}
     *
     * @return
     */
    public FTPLeechMaster getMaster() {
        return mMaster;
    }
}

