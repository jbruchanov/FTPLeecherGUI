package com.scurab.java.ftpleechergui.controller;

import com.scurab.java.ftpleechergui.model.DataEvent;
import com.scurab.java.ftpleechergui.model.FTPStorageTableModel;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 12.2.13
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
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
        mTable.getColumnModel().getColumn(2).setPreferredWidth(90);
        mTable.getColumnModel().getColumn(2).setMaxWidth(150);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        mTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
//        mTable.setDefaultRenderer(ImageIcon.class, new FTPIconCellTableRenderer());
        bind();
        createMenu();
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

    private void createMenu(){
        mMenu = new JPopupMenu();
        mMenu.add(getResourceLabel("Open"));
        mMenu.add(getResourceLabel("Download"));
    }

    @Override
    public void onShowContextMenu(JTable table, MouseEvent e) {
        FTPFile f = mTableModel.getItem(table.getSelectedRow());
        mMenu.setLabel(f != null ? f.getName() : "");
        mMenu.show(mTable, e.getX(), e.getY());
    }

    /**
     * Return selected files
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

    public String getCurrentPath(){
        return mTableModel.getCurrentPath();
    }
}
