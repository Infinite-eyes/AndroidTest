package com.demo.livedata_retrofit

import androidx.lifecycle.LiveData
import com.demo.androidtest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/21 6:16 PM
 **/
interface WanApi {
    companion object {
        fun get(): WanApi {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }

            return Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .client(clientBuilder.build())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WanApi::class.java)
        }
    }

    @GET("banner/json")
    fun bannerList(): LiveData<ApiResponse<List<BannerVO>>>


}