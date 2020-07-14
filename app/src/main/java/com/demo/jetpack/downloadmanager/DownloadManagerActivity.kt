package com.demo.jetpack.downloadmanager

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R
import java.io.File

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/14 9:51 AM
 **/
class DownloadManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jetpack_downloadmanager_activity)
        downloadByManager()
    }

    private val downloadManager by lazy {
        getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    private var downloadId = 0L

    fun downloadByManager() {
        val downloadRequest =
            DownloadManager.Request(Uri.parse("https://study.163.com/pub/study-android-official.apk"))
                .apply {
                    setAllowedOverRoaming(false)
                    setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    setTitle("Download Update")
                    setDescription("Download progress running")
                    setVisibleInDownloadsUi(true)
                    val file =
                        File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "study163.apk")
                    setDestinationUri(Uri.fromFile(file))
                }
        downloadId = downloadManager.enqueue(downloadRequest)
        registerReceiver(downloadReceiver, IntentFilter(DownloadManager.ACTION_VIEW_DOWNLOADS))
    }


    private val downloadReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val query = DownloadManager.Query()
            query.setFilterById(downloadId)
            val cursor = downloadManager.query(query)
            if (!cursor.moveToFirst()) return

            Log.d(
                "--wh--", when (cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) {
                    DownloadManager.STATUS_PAUSED -> "PAUSED"
                    DownloadManager.STATUS_PENDING -> "PENDING"
                    DownloadManager.STATUS_RUNNING -> "RUNNING"
                    DownloadManager.STATUS_SUCCESSFUL -> "SUCCESSFUL"
                    DownloadManager.STATUS_FAILED -> "FAILED"
                    else -> "===>"
                }
            )


        }
    }


}