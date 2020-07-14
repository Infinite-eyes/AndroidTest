package com.demo.jetpack.downloadmanager.v2.java.downloader;

import android.content.Context;

import java.io.File;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/14 5:00 PM
 **/
class MapDownloadManager extends FileDownManager {

    public static MapDownloadManager mapDownloadManager;

    public static MapDownloadManager getInstance(Context context) {
        if (mapDownloadManager == null) {
            synchronized (MapDownloadManager.class) {
                if (mapDownloadManager == null) {
                    mapDownloadManager = new MapDownloadManager();
                }
            }
        }
        mapDownloadManager.mContext = context;
        return mapDownloadManager;
    }

    public MapDownloadManager() {
        downloadKey = "map_down_id";
        downloadVersionKey = "map_version";
    }

    @Override
    public Context getContext() {
        if (mContext == null) {
//            mContext=MyApplication.application;
        }
        return mContext;
    }

    @Override
    public String savedFileName(String url, String newVersion) {
        return "map" + newVersion + ".zip";
    }

    @Override
    public String savedFileDir() {
        return "justdelete/savedMap";
    }

    @Override
    protected void downloadSuccess(File downloadFile) {
//        super.downloadSuccess(downloadFile);
        System.out.println("map download success=============="+downloadFile.getAbsolutePath());
    }
}
