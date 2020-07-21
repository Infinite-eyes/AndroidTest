package com.demo.livedata_retrofit

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.demo.androidtest.R

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/21 6:27 PM
 **/
class LiveDataRetrofitActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.glide_test_activity)
        loadData()
    }

    fun loadData() {
        val bannerList = WanApi.get().bannerList()
        bannerList.observe(this, Observer {
            Log.e("main", "res:$it")
        })
    }

}