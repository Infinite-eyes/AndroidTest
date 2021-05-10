package com.demo.jetpack.downloadmanager.v2.java;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.androidtest.R;
import com.demo.jetpack.downloadmanager.utils.DownloadUtils;
import com.demo.jetpack.downloadmanager.v2.java.downloader.APKDownloadManager;

import java.io.File;

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/14 5:38 PM
 **/
public class DownloadManager2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jetpack_downloadmanager_activity);

//        APKDownloadManager.getInstance(this).downOrSuccessAction(
//                "https://study.163.com/pub/study-android-official.apk"
//                , "1.4");
        new DownloadUtils(this, "https://study.163.com/pub/study-android-official.apk", "1.4.apk");

        Intent intent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(intent);
    }

}
