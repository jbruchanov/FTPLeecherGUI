package com.scurab.java.ftpleechergui;

import com.scurab.java.ftpleechergui.controller.ApplicationController;

import java.util.ResourceBundle;

public class Application {

    private static Application sSelf;

    private ResourceBundle mLables;

    private ApplicationController mAppController;

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

    public static ResourceBundle getLabels() {
        return sSelf.mLables;
    }

    public void start() {
        onLoadResources();
        mAppController = new ApplicationController();
        mAppController.start();
    }

    protected void onLoadResources() {
        mLables = ResourceBundle.getBundle("Labels");
    }

    public void showStatusBarMessage(String msg, int type) {
        mAppController.showStatusBarMessage(msg, type);
    }

    public void showProgress(boolean value) {
        mAppController.showProgress(value);
    }
}

