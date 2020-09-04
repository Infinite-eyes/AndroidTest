package com.demo.coroutines.v1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R
import kotlinx.coroutines.*

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/9/3 2:53 PM
 **/
class CoroutinesActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.coroutines_v1_activity)
//
//        Log.e("TAG", "主线程id：${mainLooper.thread.id}")
////        test()
//
////        这是最常用的用于启动协程的方式，它最终返回一个Job类型的对象，这个Job类型的对象实际上是一个接口，它包涵了许多我们常用的方法
//        val job = GlobalScope.launch {
//            delay(6000)
//            Log.e("TAG","协程执行结束 -- 线程id:${Thread.currentThread().id}")
//        }
//
//        Log.e("TAG", "协程执行结束")
//
//
//    }
//
////    runBlocking启动的协程任务会阻断当前线程，直到该协程执行结束。当协程执行结束之后，页面才会被显示出来。
//    //v1 runBlocking:T
//    private fun test() = runBlocking {
//        repeat(8) {
//            Log.e("TAG", "协程执行$it 线程id：${Thread.currentThread().id}")
//            delay(1000)
//        }
//    }


//    //v3协程体
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.coroutines_v1_activity)
//
//        GlobalScope.launch {
//            val token = getToken();
//            val userInfo = getUserInfo(token)
//            setUserInfo(userInfo)
//        }
//
//        repeat(8){
//            Log.e("TAG","主线程执行$it")
//        }
//    }
//
//    private fun setUserInfo(userInfo: String){
//        Log.e("TAG",userInfo)
//    }
//
//
//    private suspend fun getToken(): String {
//        delay(2000)
//        return "token"
//    }
//
//    private suspend fun getUserInfo(token: String): String {
//        delay(2000)
//        return "$token - userInfo"
//    }


    //async
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutines_v1_activity)


        GlobalScope.launch {
            val result1 = GlobalScope.async {
                getResult1()
            }

            val result2 = GlobalScope.async {
                getResult2()
            }

            val result = result1.await() + result2.await()

            Log.e("TAG", "result = $result")
        }
    }

    private suspend fun getResult1(): Int {
        delay(3000)
        return 1
    }

    private suspend fun getResult2(): Int {
        delay(4000)
        return 2
    }


}