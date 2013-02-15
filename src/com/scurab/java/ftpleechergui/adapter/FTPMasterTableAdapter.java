package com.scurab.java.ftpleechergui.adapter;

import com.scurab.java.ftpleecher.FTPDownloadThread;
import com.scurab.java.ftpleecher.FTPLeechMaster;
import com.scurab.java.ftpleecher.FatalFTPException;
import com.scurab.java.ftpleecher.NotificationAdapter;
import com.scurab.java.ftpleechergui.model.DownloadTableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Main bridge class for communication between {@link FTPLeechMaster} and {@link DownloadTableModel}
 */
public class FTPMasterTableAdapter implements NotificationAdapter {

    private FTPLeechMaster mMaster;

    private List<DownloadTableModel> mObservers;

    public FTPMasterTableAdapter(FTPLeechMaster master) {
        if (master == null) {
            throw new IllegalArgumentException("FTPLeechMaster is null!");
        }
        mMaster = master;
        mMaster.setNotificationAdapter(this);
    }

    public FTPDownloadThread getItem(int index) {
        return mMaster.getItem(index);
    }

    public int getItemsCount() {
        return mMaster.size();
    }

    /**
     * Notify registered observers about big change in dataset
     */
    @Override
    public void performNotifyDataChanged() {
        if (mObservers != null) {
            synchronized (mObservers) {
                for (DownloadTableModel ob : mObservers) {
                    ob.fireTableDataChanged();
                }
            }
        }
    }

    /**
     * Notify registered observers about change in one particular thread
     *
     * @param thread
     */
    @Override
    public void performNotifyDataChanged(FTPDownloadThread thread) {
        if (mObservers != null) {
            synchronized (mObservers) {
                int index = thread.getIndex();
                for (DownloadTableModel ob : mObservers) {
                    ob.fireTableRowsUpdated(index, index);
                }
            }
        }
    }

    /**
     * Register listener
     *
     * @param downloadTableModel
     */
    public void registerObserver(DownloadTableModel downloadTableModel) {
        if (mObservers == null) {
            mObservers = new ArrayList<DownloadTableModel>();
        }
        synchronized (mObservers) {
            mObservers.add(downloadTableModel);
        }
    }

    /**
     * Unregister listener
     *
     * @param downloadTableModel
     */
    public void unregisterObserver(DownloadTableModel downloadTableModel) {
        if (mObservers != null) {
            synchronized (mObservers) {
                mObservers.remove(downloadTableModel);
            }
        }
    }

    //region notifications
    @Override
    public void onError(FTPDownloadThread source, Exception e) {
        performNotifyDataChanged(source);
    }

    @Override
    public void onFatalError(FTPDownloadThread source, FatalFTPException e) {
        performNotifyDataChanged(source);
    }

    @Override
    public void onDownloadProgress(FTPDownloadThread source, double down, double downPerSec) {
        performNotifyDataChanged(source);
    }

    @Override
    public void onStatusChange(FTPDownloadThread source, FTPDownloadThread.State state) {
        performNotifyDataChanged(source);
    }

    //endregion notifications
}

