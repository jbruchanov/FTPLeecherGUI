package com.scurab.java.ftpleechergui.controller;

import com.scurab.java.ftpleechergui.model.FileStorageTableModel;

import javax.swing.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 12.2.13
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class LocalStorageController extends TableController {

    private JTable mTable;
    private FileStorageTableModel mTableModel;

    public LocalStorageController(JTable tree) {
        super(tree);
        mTable = tree;
        init();
    }

    private void init() {
        mTableModel = new FileStorageTableModel();
        mTable.setModel(mTableModel);
        mTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    @Override
    public void onRowDoubleClick(JTable table, int rowIndex) {
        onOpenFolder(mTable, mTableModel.getItem(rowIndex));
    }

    public void onOpenFolder(JTable source, File f) {
        try {
            mTableModel.setRoot(f);
        } catch (Exception e) {
            showStatusBarMessage(e.getMessage());
        }
    }
}
