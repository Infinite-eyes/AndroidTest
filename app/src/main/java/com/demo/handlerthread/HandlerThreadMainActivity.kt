package com.demo.handlerthread

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/31 11:25 AM
 **/
class HandlerThreadMainActivity : AppCompatActivity() {

    lateinit var mainHandler: Handler
    lateinit var workHandler: Handler
    lateinit var mHandlerThread: HandlerThread
    lateinit var text: TextView
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.handlerthread_activity)

        text = findViewById(R.id.text1);


        // 创建与主线程关联的Handler
        mainHandler = Handler()


        /**
         * 步骤1：创建HandlerThread实例对象
         * 传入参数 = 线程名字，作用 = 标记该线程
         */
        mHandlerThread = HandlerThread("handlerThread");
        mHandlerThread.start();

        workHandler = object : Handler(mHandlerThread.looper) {

            override fun handleMessage(msg: Message) {

                when (msg.what) {

                    1 -> {
                        try {
                            Thread.sleep(1000);
                        } catch (e: InterruptedException) {
                            e.printStackTrace();
                        }

                        mainHandler.post(object : Runnable {
                            override fun run() {
                                text.text = "我爱学习";
                            }
                        });
                    }

                    2 -> {
                        try {
                            Thread.sleep(3000);
                        } catch (e: InterruptedException) {
                            e.printStackTrace();
                        }
                        mainHandler.post {
                            text.text = "我不喜欢学习"
                        }
                    }
                }
            }
        }

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            var msg = Message.obtain();
            msg.what = 1;
            msg.obj = "A";
            workHandler.sendMessage(msg)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            var msg = Message.obtain();
            msg.what = 2;
            msg.obj = "B";
            workHandler.sendMessage(msg);
        }

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener {
            mHandlerThread.quit();
        }


    }

}