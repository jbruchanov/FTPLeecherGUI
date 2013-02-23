package com.scurab.java.ftpleechergui.table;

import com.scurab.java.ftpleecher.FTPContext;
import com.scurab.java.ftpleecher.FTPDownloadThread;
import com.scurab.java.ftpleechergui.TextUtils;
import com.scurab.java.ftpleechergui.model.DownloadTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;


public class DownloadTableCellRenderer extends DefaultTableCellRenderer {

    private final Color LIGHT_RED;
    private final Color LIGHT_GREEN;
    private final Color LIGHT_YELLOW;
    private final Color WHITE_SMOKE;

    public DownloadTableCellRenderer() {

        LIGHT_RED = Color.decode("#FFC8C8");
        LIGHT_GREEN = Color.decode("#C8FFC8");
        LIGHT_YELLOW = Color.decode("#FFFFC8");
        WHITE_SMOKE = Color.decode("#D3D3D3");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        DownloadTableModel model = (DownloadTableModel)table.getModel();
        Component c = super.getTableCellRendererComponent(table, formatValue(value, column), isSelected, hasFocus, row, column);
        int sRow = table.convertRowIndexToModel(row);
        c.setBackground(getColor(model.getItemAtIndex(sRow).getFtpState(), row));
        return c;
    }

    private Color getColor(FTPDownloadThread.State value, int row){
        switch (value){
            case Downloading:
            case Merging:
            case Paused:
                return LIGHT_YELLOW;
            case Downloaded:
            case Finished:
                return LIGHT_GREEN;
            case Error:
            case FatalError:
            case WaitingForRetry:
                return LIGHT_RED;
            default:
                return row % 2 == 0 ? Color.WHITE : WHITE_SMOKE;
        }
    }

    private Object formatValue(Object value, int column){
        switch (column) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 8:
                return value;
            case 4:
                return TextUtils.getNumberReadable((Long)value);
            case 5:
                return TextUtils.getNumberReadable((Long)value);
            case 6:
                return value + "%";
            case 7:
                return TextUtils.getSpeedReadable((Integer)value);
            default:
                return "Not implemented";
        }
    }

    private String getException(Throwable e) {
        return e != null ? e.getMessage() : "";
    }
}
