package com.scurab.java.ftpleechergui.controller;

import com.scurab.java.ftpleecher.*;
import com.scurab.java.ftpleechergui.adapter.FTPMasterTableAdapter;
import com.scurab.java.ftpleechergui.model.DownloadTableModel;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 14.2.13
 * Time: 0:01
 * To change this template use File | Settings | File Templates.
 */
public class DownloadController extends TableController {

    private JTable mTable;

    private FTPLeechMaster mMaster;

    private FactoryConfig mConfig;

    private List<DownloadTask> mTasks = new ArrayList<DownloadTask>();

    private FTPFactory mFactory;

    private DownloadTableModel mTableModel;

    private FTPMasterTableAdapter mAdapter;

    public DownloadController(JTable table, FTPLeechMaster master) {
        super(table);
        mTable = table;
        mTable.setFont(new Font(mTable.getFont().getFontName(), Font.PLAIN, 11));
        mMaster = master;
        mAdapter = new FTPMasterTableAdapter(mMaster);
        mTableModel = new DownloadTableModel(mAdapter);
        mTable.setModel(mTableModel);
    }

    @Override
    protected void onInitTable(JTable table) {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setRowMargin(2);
        table.setRowHeight(25);
    }

    public void onDownloadItem(String ftpFolder, FTPFile file, String destFolder) throws IOException, FatalFTPException {
        String url = ftpFolder + "/" + file.getName();
        DownloadTask task = mFactory.createTask(url, destFolder);
        mTasks.add(task);
        mMaster.enqueue(task);
    }

    public FactoryConfig getConfig() {
        return mConfig;
    }

    public void setConfig(FactoryConfig config) {
        mConfig = config;
        mFactory = new FTPFactory(mConfig);
    }
}
