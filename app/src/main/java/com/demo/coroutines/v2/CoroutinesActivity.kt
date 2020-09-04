package com.demo.coroutines.v2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/9/3 4:42 PM
 **/
class CoroutinesActivity : AppCompatActivity() {

//    Dispatchers.Default
//    Dispatchers.IO -
//    Dispatchers.Main - 主线程
//    Dispatchers.Unconfined - 没指定，就是在当前线程


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutines_v2_activity)



        GlobalScope.launch(Dispatchers.Unconfined) {
            Log.d("AA", "协程初始化完成,时间：" + System.currentTimeMillis())
            for (i in 1..3) {
                Log.d("AA", "协程任务1打印第$i 次,时间: " + System.currentTimeMillis())
            }

            delay(500)
            for (i in 1..3) {
                Log.d("AA", "协程任务2打印第$i 次,时间：" + System.currentTimeMillis())
            }

        }

        Log.d("AA", "主线程 sleep, 时间：" + System.currentTimeMillis())
        Thread.sleep(1000)
        Log.d("AA", "主线程运行, 时间：" + System.currentTimeMillis())

        for (i in 1..3) {
            Log.d("AA", "主线程打印第$i 次，时间：" + System.currentTimeMillis())
        }


    }


}