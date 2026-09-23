package com.scurab.java.ftpleechergui.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scurab.java.ftpleecher.FTPConnection;
import com.scurab.java.ftpleecher.FTPContext;
import com.scurab.java.ftpleechergui.Application;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class BaseController {

    public FTPConnection[] getSavedConnections() {
        return application().getConnections();
    }

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

    public void showInfo(String msg) {
        showMessageBox(msg, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String msg) {
        showMessageBox(msg, JOptionPane.ERROR_MESSAGE);
    }

    public void showMessageBox(String msg, int type) {
        final String text = msg != null ? msg : getResourceLabel("UnknownError");
        runOnUiThread(() -> JOptionPane.showMessageDialog(getView(), text, "", type));
    }

    /**
     * Runs on the EDT; Swing touched from other threads shows blank, unclosable dialogs.
     */
    public static void runOnUiThread(Runnable r) {
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            SwingUtilities.invokeLater(r);
        }
    }

    public Application application() {
        return Application.getInstance();
    }

    public abstract Component getView();
}
