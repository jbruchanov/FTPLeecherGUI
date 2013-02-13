package com.scurab.java.ftpleechergui.controller;

import com.scurab.java.ftpleechergui.window.MainWindow;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 12.2.13
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationController extends BaseController {

    private MainWindowController mController;

    public void start() {
        MainWindow mw = onCreateMainWindow();
        mController = new MainWindowController(mw);
        mController.onCreate();

        mw.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); //calling the method is a must
            }
        });
    }

    public MainWindow onCreateMainWindow() {
        MainWindow mf = new MainWindow();
        mf.setBounds(10, 10, 640, 480);
        return mf;
    }

    @Override
    public void showStatusBarMessage(String msg, int type) {
        mController.showStatusBarMessage(msg, type);
    }

    @Override
    public void showProgress(boolean value) {
        mController.showProgress(value);
    }
}
