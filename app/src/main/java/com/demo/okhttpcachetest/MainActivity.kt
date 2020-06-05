package com.demo.okhttpcachetest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var baseService: Service? = null;
    private var observable: Observable<ResponseBody>? = null;
    private var observer: Observer<ResponseBody>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cacheFile = File(applicationContext.cacheDir, "cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 20) // 缓存大小20MB

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(CacheInterceptor())
//        builder.addNetworkInterceptor(CacheInterceptor())
        builder.cache(cache);

        var retrofit = Retrofit.Builder()
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://49.235.62.163:3000/")
            .build()

        baseService = retrofit.create(Service::class.java)

        observable = baseService?.send();

        observer = object : Observer<ResponseBody> {
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
            override fun onNext(t: ResponseBody) {
            }
        }


    }

    fun onClick(v: View) {

        observable?.subscribeOn(Schedulers.io())
            ?.unsubscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(observer as Observer<ResponseBody>)

    }


    interface Service {
        //发送短信
        @GET("/index.html")
//        @GET(".")
        fun send(): Observable<ResponseBody>
    }


    internal class CacheInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request)
            return response.newBuilder() // 长缓存,有效期为7天
//
                .removeHeader("Pragma")
                .header("Cache-Control","max-age=60")
//                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
//                .header("Cache-Control", "public, only-if-cached, max-stale=" + 30 )
                .build()
        }
    }

}
