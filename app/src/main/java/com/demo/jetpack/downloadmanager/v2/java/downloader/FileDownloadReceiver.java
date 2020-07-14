package com.demo.jetpack.downloadmanager.v2.java.downloader;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import java.io.File;
import java.util.Arrays;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/14 5:02 PM
 **/
public class FileDownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("intent=======action===" + intent.getAction());

        if (TextUtils.equals(intent.getAction(), DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (reference == -1) {
                return;
            }
            String filePath = getFilePath(context, reference);
            File file = new File(filePath);

            if (file.exists() && file.length() > 0) {
                APKDownloadManager apkDownloadManager = APKDownloadManager.getInstance(context);
                if (reference == apkDownloadManager.getDownloadID()) {
                    apkDownloadManager.downloadSuccess(file);
                    return;
                }
                MapDownloadManager mapDownloadManager = MapDownloadManager.getInstance(context);
                if (reference == mapDownloadManager.getDownloadID()) {
                    mapDownloadManager.downloadSuccess(file);
                    return;
                }
            }
        } else if (TextUtils.equals(intent.getAction(), DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
            long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
            System.out.println("ids==============" + Arrays.toString(ids));
        }
    }

    private String getFilePath(Context context, long id) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor c = ((DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE)).query(query);
        if (c != null && c.moveToFirst()) {
//            String name = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
            int fileUriIdx = c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
            String fileUri = c.getString(fileUriIdx);
            String fileName = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (fileUri != null) {
                    fileName = Uri.parse(fileUri).getPath();
                }
            } else {
                //Android 7.0以上的方式：请求获取写入权限，这一步报错
                //过时的方式：DownloadManager.COLUMN_LOCAL_FILENAME
                int fileNameIdx = c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                fileName = c.getString(fileNameIdx);
            }
            return fileName;
        }
        return "";
    }


}
