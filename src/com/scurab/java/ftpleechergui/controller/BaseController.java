package com.scurab.java.ftpleechergui.controller;

import com.scurab.java.ftpleechergui.Application;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 12.2.13
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
public class BaseController {

    public String getResourceLabel(String key) {
        return Application.getLabels().getString(key);
    }

    public void showStatusBarMessage(String s) {
        showStatusBarMessage(s, 0);
    }

    public void showStatusBarMessage(String s, int type) {
        Application.getInstance().showStatusBarMessage(s, type);
    }

    public void showProgress(boolean value) {
        Application.getInstance().showProgress(value);
    }
}
