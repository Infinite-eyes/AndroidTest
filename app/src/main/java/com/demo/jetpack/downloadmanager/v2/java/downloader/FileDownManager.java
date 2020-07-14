package com.demo.jetpack.downloadmanager.v2.java.downloader;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/14 2:34 PM
 **/
public abstract class FileDownManager {

    private DownloadManager downloadManager;
    private String fileName;
    private String saveDir;
    public static String DOWNLOAD_SP_NAME = "download_sp_name";
    public Context mContext;

    public abstract Context getContext();


    public SharedPreferences getSp() {
        return getContext().getSharedPreferences(DOWNLOAD_SP_NAME, Context.MODE_PRIVATE);
    }

    public abstract String savedFileName(String url, String newVersion);

    public abstract String savedFileDir();

    public File getSavedFile() {
        return new File(Environment.getExternalStorageDirectory(), saveDir + "/" + fileName);
    }

    public static String downloadKey = "downID";
    public static String downloadVersionKey = "version";

    public long getDownloadID() {
        return getSp().getLong(downloadKey, -1);
    }

    public String getDownloadVersion() {
        return getSp().getString(downloadVersionKey, "0");
    }

    public void downOrSuccessAction(String url, String newVersion) {
        saveDir = savedFileDir();
        fileName = savedFileName(url, newVersion);
        downloadManager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        String downVersion = getSp().getString(downloadVersionKey, null);
        if (TextUtils.equals(newVersion, downVersion)) {
            long id = getSp().getLong(downloadKey, -1);
            if (id == -1) {
                down(url, newVersion);
            } else {
                query(url, id, newVersion);
            }
        } else {
            getSp().edit().putString(downloadVersionKey, newVersion).commit();
            down(url, newVersion);
        }
    }

    private void down(String url, String newVersion) {
        Uri resource = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(resource);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(false);
//        // set file mime type
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
//        request.setMimeType(mimeString);


        // show notification on the state bar
        request.setVisibleInDownloadsUi(true);


        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);

        File file =
                new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "study163.apk");

        request.setDestinationUri(Uri.fromFile(file));
//        request.setDestinationInExternalPublicDir(saveDir, fileName);


        requestCustom(request);
        try {
            long id = downloadManager.enqueue(request);
            getSp().edit().putLong(downloadKey, id).commit();
        } catch (Exception e) {
            e.printStackTrace();
            enableDownloadManager(getContext());
        }
    }

    public void requestCustom(DownloadManager.Request request) {

    }

    private void query(String url, long id, String newVersion) {
        System.out.println("query=======" + url + "===" + id + "===" + newVersion);

        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor c = downloadManager.query(query);

        if (c == null) {
            System.out.println("cursor null ========");
            redownload(url, id, newVersion);
        } else if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    Log.v("down", "STATUS_PAUSED");
                case DownloadManager.STATUS_PENDING:
                    Log.v("down", "STATUS_PENDING");
                case DownloadManager.STATUS_RUNNING:
                    Log.v("down", "STATUS_RUNNING");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    Log.v("down", "STATUS_SUCCESSFUL");
//                    String name = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                    String uri = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    String uri_d = c.getString(c.getColumnIndex(DownloadManager.COLUMN_URI));//the http url address

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

                    //name=/storage/emulated/0/justdelete/apkdown/appname1.2.apk
                    //file:///storage/emulated/0/justdelete/apkdown/appname1.2.apk

                    File savedFile = getSavedFile();
                    System.out.println("name=" + fileName + "==" + uri + "===" + uri_d + "=====" + savedFile.getAbsolutePath());
                    if (savedFile.exists()) {
                        downloadSuccess(savedFile);
                    } else {
                        System.out.println("file not exist");
                        redownload(url, id, newVersion);
                    }
                    break;
                case DownloadManager.STATUS_FAILED:
                    Log.v("down", "STATUS_FAILED");
                    redownload(url, id, newVersion);
                    break;
                default:
                    Log.v("down", "STATUS_=====" + status);
                    break;
            }
        }
    }

    private void redownload(String url, long id, String newVersion) {
        downloadManager.remove(id);
        getSp().edit().remove(downloadKey).apply();
        down(url, newVersion);
    }

    protected void downloadSuccess(File downloadFile) {
        System.out.println("downloadfile size======" + downloadFile.length());
    }


    public static void enableDownloadManager(Context context) {
        try {
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:com.android.providers.downloads"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }


}
