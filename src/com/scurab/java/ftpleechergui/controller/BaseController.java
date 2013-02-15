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

    public static final String CONNECTIONS_FILE = "connections.json";

    private static FTPConnection[] mSavedConnections;

    private static final Gson sGson = new GsonBuilder().setPrettyPrinting().create();

    public BaseController() {
        if (mSavedConnections == null) {
            mSavedConnections = onLoadSavedConnections();
        }
    }

    /**
     * Load saved connections from connections.json file
     *
     * @return
     */
    protected FTPConnection[] onLoadSavedConnections() {
        FTPConnection[] result = null;
        try {
            File f = new File(CONNECTIONS_FILE);
            if (f.exists() && f.isFile()) {
                String values = IOUtils.toString(new FileInputStream(f));
                result = sGson.fromJson(values, FTPConnection[].class);
            } else {
                result = new FTPContext[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
//            showStatusBarMessage(e.getMessage());
            result = new FTPContext[0];
        }
        return result;
    }

    /**
     * Save connections to connections.json file
     *
     * @param values
     * @throws IOException
     */
    public void saveConnections(FTPConnection... values) throws IOException {
        File f = new File(CONNECTIONS_FILE);
        f.delete();
        FileOutputStream fos = new FileOutputStream(f);
        String json = sGson.toJson(values);
        fos.write(json.getBytes());
        fos.close();
    }

    public FTPConnection[] getSavedConnections() {
        return mSavedConnections;
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
        JOptionPane.showMessageDialog(getView(), msg, "", type);
    }

    public Application application() {
        return Application.getInstance();
    }

    public abstract Component getView();
}
