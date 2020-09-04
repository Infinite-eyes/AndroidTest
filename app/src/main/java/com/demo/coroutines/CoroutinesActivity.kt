package com.demo.coroutines

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.io.IOException
import java.net.ConnectException
import kotlin.coroutines.CoroutineContext


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/7 4:34 PM
 **/
class CoroutinesActivity : AppCompatActivity(), CoroutineScope {


//    launch  - 创建协程
//    async   - 创建带返回值的协程，返回的是 Deferred 类
//    withContext - 不创建新的协程，在指定协程上运行代码块
//    runBlocking -不是 GlobalScope 的 API，可以独立使用，区别是 runBlocking 里面的 delay 会阻塞线程，而 launch 创建的不会


    //v1
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.coroutines_activity)
//
//        Log.e("TAG", "主线程id：${mainLooper.thread.id}")
////        System.out.print("TAG 主线程id：${mainLooper.thread.id}");
//        test()
//        Log.e("TAG", "协程执行结束")
////        System.out.print("TAG 协程执行结束");
//    }
//
//    private fun test() = runBlocking {
//        repeat(8) {
//            Log.e("TAG", "协程执行$it 线程id：${Thread.currentThread().id}")
////            System.out.print("TAG 协程执行$it 线程id：${Thread.currentThread().id}");
//            delay(1000)
//        }
//    }

    //v2

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.coroutines_activity)
//
//        Log.e("TAG", "主线程id：${mainLooper.thread.id}")
//        val job = GlobalScope.launch {
//            delay(6000)
//            Log.e("TAG",  "协程执行结束 -- 线程id：${Thread.currentThread().id}" )
//        }
//
//        Log.e("TAG","主线程执行结束")
//
//
//        job.isActive
//        job.isCancelled
//        job.isCompleted
//        job.cancel()
////        job.join()
//
//    }


    //v3
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.coroutines_activity)
//
//        GlobalScope.launch {
//            val token = getToken()
//            val userInfo = getUserInfo(token)
//            setUserInfo(userInfo)
//        }
//
//        repeat(8){
//            Log.e("TAG", "主线程执行$it")
//        }
//    }
//
//    private fun setUserInfo(userInfo: String) {
//        Log.e("TAG", userInfo)
//    }
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
//


    interface Api {

        @POST("user/login")
        suspend fun login(@Body user: User): Call<User>
    }


    fun <T> CoroutineScope.retrofit(dsl: RetrofitCoroutineDSL<T>.() -> Unit) {
        this.launch(Dispatchers.Main) {
            //            val coroutine = RetrofitCoroutineDSL<T>()
//            coroutine.dsl()

            val coroutine = RetrofitCoroutineDSL<T>().apply(dsl)

            coroutine.api?.let { call ->
                val deferred = async(Dispatchers.IO) {
                    try {
                        call.execute()
                    } catch (e: ConnectException) {
                        coroutine.onFail?.invoke("网络连接出错", -1)
                        null
                    } catch (e: IOException) {
                        coroutine.onFail?.invoke("未知网络错误", -1)
                        null
                    }
                }

                deferred.invokeOnCompletion {
                    if (deferred.isCancelled) {
                        call.cancel()
                        coroutine.clean()
                    }
                }

                val response = deferred.await()
                if (response == null) {
                    coroutine.onFail?.invoke("返回为空", -1)
                } else {
                    response.let {
                        if (response.isSuccessful) {
//                            if (response.body()?.status == 1) {
                            if (response.body() != null) {
//                                coroutine.onSuccess?.invoke(response.body()!!)
                            } else {
//                                coroutine.onFail?.invoke(response.body()!!)
                            }
                        } else {
                            coroutine.onFail?.invoke(
                                response.errorBody().toString(),
                                response.code()
                            )
                        }
                    }
                }
                coroutine.onComplete?.invoke()
            }

        }
    }


    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutines_activity)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}

class RetrofitCoroutineDSL<T> {

    var api: (Call<Result<T>>)? = null

    internal var onSuccess: ((T) -> Unit)? = null
        private set
    internal var onFail: ((msg: String, errorCode: Int) -> Unit)? = null
        private set

    internal var onComplete: (() -> Unit)? = null
        private set


    fun onSuccess(block: (T) -> Unit) {
        this.onSuccess = block
    }

    fun onFail(block: (msg: String, errorCode: Int) -> Unit) {
        this.onFail = block
    }

    fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }

    internal fun clean() {
        onSuccess = null
        onComplete = null
        onFail = null
    }

}

