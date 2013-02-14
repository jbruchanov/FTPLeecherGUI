package com.scurab.java.ftpleechergui.controller;

import com.google.gson.*;
import com.scurab.java.ftpleecher.FTPFactory;
import com.scurab.java.ftpleecher.FactoryConfig;
import com.scurab.java.ftpleechergui.Application;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 12.2.13
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseController {

    public static final String CONNECTIONS_FILE = "connections.json";
    private static FactoryConfig[] mSavedConnections;

    private static final Gson sGson = new GsonBuilder().setPrettyPrinting().create();

    public BaseController() {
        if(mSavedConnections == null){
            mSavedConnections = onLoadSavedConnections();
        }
    }

    /**
     * Load saved connections from connections.json file
     * @return
     */
    protected FactoryConfig[] onLoadSavedConnections() {
        FactoryConfig[] result = null;
        try{
            File f = new File(CONNECTIONS_FILE);
            if(f.exists() && f.isFile()){
                String values = IOUtils.toString(new FileInputStream(f));
                result = sGson.fromJson(values, FactoryConfig[].class);
            }else{
                result = new FactoryConfig[0];
            }
        }catch(Exception e){
            e.printStackTrace();
//            showStatusBarMessage(e.getMessage());
            result = new FactoryConfig[0];
        }
        return result;
    }

    /**
     * Save connections to connections.json file
     * @param values
     * @throws IOException
     */
    public void saveConnections(FactoryConfig... values) throws IOException {
        File f = new File(CONNECTIONS_FILE);
        f.delete();
        FileOutputStream fos = new FileOutputStream(f);
        String json = sGson.toJson(values);
        fos.write(json.getBytes());
        fos.close();
    }

    public FactoryConfig[] getSavedConnections(){
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

    public void showInfo(String msg){
        showMessageBox(msg, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String msg){
        showMessageBox(msg, JOptionPane.ERROR_MESSAGE);
    }

    public void showMessageBox(String msg, int type){
        JOptionPane.showMessageDialog(getView(), msg, "", type);
    }

    public Application application(){
        return Application.getInstance();
    }

    public abstract Component getView();
}
