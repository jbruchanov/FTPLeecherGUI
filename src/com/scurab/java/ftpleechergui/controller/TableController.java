package com.scurab.java.ftpleechergui.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class TableController extends BaseController {

    private JTable mTable;

    public TableController(JTable table) {
        mTable = table;
        onInitTable(mTable);
        mTable.setVisible(true);
        bind();
    }

    protected void onInitTable(JTable table) {
        table.setFont(new Font(table.getFont().getFontName(), Font.PLAIN, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setRowMargin(2);
        table.setRowHeight(25);
    }

    private void bind() {
        mTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    onRowDoubleClick(mTable, mTable.getSelectedRow());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int r = mTable.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < mTable.getRowCount()) {
                        mTable.setRowSelectionInterval(r, r);
                    } else {
                        mTable.clearSelection();
                    }
                    onShowContextMenu(mTable, e);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public void onShowContextMenu(JTable table, MouseEvent e) {
    }

    public void onRowDoubleClick(JTable table, int rowIndex) {
    }

    public Component getView() {
        return mTable;
    }
}
