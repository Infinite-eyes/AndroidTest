package com.demo.jetpack.downloadmanager.v2.java.downloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/14 4:41 PM
 **/
public class APKDownloadManager extends FileDownManager {

    public static volatile APKDownloadManager apkDownloadManager;

    public static APKDownloadManager getInstance(Context context) {
        if (apkDownloadManager == null) {
            synchronized (APKDownloadManager.class) {
                if (apkDownloadManager == null) {
                    apkDownloadManager = new APKDownloadManager();
                }
            }
        }
        apkDownloadManager.mContext = context;
        return apkDownloadManager;
    }

    public APKDownloadManager() {
        downloadKey = "apk_down_id";
        downloadVersionKey = "apk_version";
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
        return "appname" + newVersion + ".apk";
    }

    @Override
    public String savedFileDir() {
        return "justdelete/apkdown";
    }

    @Override
    protected void downloadSuccess(File downloadFile) {
        super.downloadSuccess(downloadFile);
        System.out.println("apk download success=================" + downloadFile.getAbsolutePath());
        installApk(mContext, downloadFile);
    }


    private void installApk(Context context, File file) {
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (!(mContext instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, "com.demo.jetpack.downloadmanager.DownloadFileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            }
        } else {
            uri = Uri.fromFile(file);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }




}
