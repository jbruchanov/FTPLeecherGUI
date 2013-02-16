package com.scurab.java.ftpleechergui.controller;

import com.scurab.java.ftpleechergui.model.DataEvent;
import com.scurab.java.ftpleechergui.model.FTPStorageTableModel;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseEvent;

public class FTPController extends TableController {

    private JTable mTable;

    private FTPStorageTableModel mTableModel;

    private FTPClient mClient;

    private JPopupMenu mMenu;

    public FTPController(JTable table) {
        super(table);
        mTable = table;
        mTableModel = new FTPStorageTableModel();
        mTable.setModel(mTableModel);
        mTable.getColumnModel().getColumn(0).setMaxWidth(30);
        mTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        mTable.getColumnModel().getColumn(2).setMaxWidth(200);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        mTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        bind();
    }

    @Override
    protected void onInitTable(JTable table) {
        super.onInitTable(table);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    private void bind() {
        mTableModel.setOnFtpStorageEventListener(new FTPStorageTableModel.OnFtpStorageEventListener() {
            @Override
            public void onDataEvent(DataEvent type) {
                showProgress(type == DataEvent.Started);
            }

            @Override
            public void onError(Throwable t) {
                showStatusBarMessage(t.getMessage());
            }
        });
    }

    @Override
    public void onRowDoubleClick(JTable table, int rowIndex) {
        FTPFile file = mTableModel.getItem(rowIndex);
        if (!mTableModel.isLoading()) {
            if (file == null || file.isDirectory()) {
                mTableModel.setRoot(file);
            }
        }
    }

    public void setFTPClient(FTPClient client) {
        mClient = client;
        mTableModel.setFTPClient(mClient);
    }

    /**
     * Return selected files
     *
     * @return
     */
    public FTPFile[] getSelectedItems() {
        int[] rows = mTable.getSelectedRows();
        FTPFile[] result = new FTPFile[rows.length];
        for (int i = 0; i < rows.length; i++) {
            result[i] = mTableModel.getItem(rows[i]);
        }
        return result;
    }

    public String getCurrentPath() {
        return mTableModel.getCurrentPath();
    }
}
