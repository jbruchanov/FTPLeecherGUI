package com.scurab.java.ftpleechergui.model;

import com.scurab.java.ftpleecher.FTPContext;
import com.scurab.java.ftpleecher.FTPDownloadThread;
import com.scurab.java.ftpleechergui.Application;
import com.scurab.java.ftpleechergui.TextUtils;
import com.scurab.java.ftpleechergui.adapter.FTPMasterTableAdapter;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 13.2.13
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
public class DownloadTableModel extends AbstractTableModel {

    /*
        0 Index
        1 State
        2 FileName
        3 Part
        4 Size
        5 Downloaded
        6 percents
        7 Speed
        8 Error
     */

    private FTPMasterTableAdapter mAdapter;



    public DownloadTableModel(FTPMasterTableAdapter adapter) {


        adapter.registerObserver(this);
        mAdapter = adapter;
    }

    @Override
    public int getRowCount() {
        return mAdapter.getItemsCount();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, final int columnIndex) {
        final FTPDownloadThread thread = mAdapter.getItem(rowIndex);
        final FTPContext config = thread.getContext();
        switch (columnIndex){
            case 0: return thread.getIndex();
            case 1: return thread.getFtpState();
            case 2: return config.fileName;
            case 3: return thread.getPart() != -1 ? String.format("%03d",thread.getPart()) : "";
            case 4: return TextUtils.getNumberReadable(config.currentPieceLength);
            case 5: return TextUtils.getNumberReadable((int)thread.getDownloaded());
            case 6: return ((int)((thread.getDownloaded() / (float)config.currentPieceLength)*100)) + "%";
            case 7: return TextUtils.getSpeedReadable(thread.getSpeed());
            case 8: return getException(thread.getException());
            default:
                return "Not implemented";
        }
    }

    private String getException(Throwable e){
        return e != null ? e.getMessage() : "";
    }
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return Application.getLabels().getString("Index");
            case 1: return Application.getLabels().getString("State");
            case 2: return Application.getLabels().getString("Filename");
            case 3: return Application.getLabels().getString("Part");
            case 4: return Application.getLabels().getString("Size");
            case 5: return Application.getLabels().getString("Downloaded");
            case 6: return Application.getLabels().getString("Percents");
            case 7: return Application.getLabels().getString("Speed");
            case 8: return Application.getLabels().getString("Error");
            default:
                return "Not implemented";
        }
    }


}
