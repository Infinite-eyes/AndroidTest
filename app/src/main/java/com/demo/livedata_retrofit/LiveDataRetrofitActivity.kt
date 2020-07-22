package com.demo.livedata_retrofit

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.bingoogolapple.bgabanner.BGABanner
import com.demo.androidtest.R
import com.demo.androidtest.databinding.LivedataRetrofitActivityBinding


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/21 6:27 PM
 **/
class LiveDataRetrofitActivity : AppCompatActivity() {

    lateinit var binding: LivedataRetrofitActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.livedata_retrofit_activity
        )
        val vm = ViewModelProviders.of(this).get(HomeVM::class.java)
        binding.lifecycleOwner = this
        binding.vm = vm
        initBanner()
    }

    private fun initBanner(){
        binding.run{
            val bannerAdapter = BGABanner.Adapter<ImageView,BannerVO>{_,image,model,_ ->
                image.displayWithUrl(model?.imagePath)
            }
            banner.setAdapter(bannerAdapter)
            vm?.banners?.observe(this@LiveDataRetrofitActivity, Observer {
                banner.setData(it,null)
            })
        }
    }


}

