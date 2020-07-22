package com.demo.livedata_retrofit.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import cn.bingoogolapple.bgabanner.BGABanner
import com.demo.androidtest.R
import com.demo.androidtest.databinding.LivedataRetrofitActivityBinding
import com.demo.livedata_retrofit.model.BannerVO
import com.demo.livedata_retrofit.viewmodel.HomeVM
import com.demo.livedata_retrofit.viewmodel.LoadingObserver


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/21 6:27 PM
 **/
class LiveDataRetrofitActivity : AppCompatActivity() {

    lateinit var binding: LivedataRetrofitActivityBinding

    val loadingState = MutableLiveData<Boolean>()

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

    private fun initBanner() {
        binding.run {

//            dialog v1
//            vm.loading.observe(
//                this@LiveDataRetrofitActivity,
//                LoadingObserver(this@LiveDataRetrofitActivity)
//            )
//            dialog v2
            loadingState.observe(
                this@LiveDataRetrofitActivity,
                LoadingObserver(this@LiveDataRetrofitActivity)
            )
            vm?.attachLoading(loadingState)


            //banner
            val bannerAdapter = BGABanner.Adapter<ImageView, BannerVO> { _, image, model, _ ->
                image.displayWithUrl(model?.imagePath)
            }
            banner.setAdapter(bannerAdapter)
            vm?.banners?.observe(this@LiveDataRetrofitActivity, Observer {
                banner.setData(it, null)
            })


        }
    }


}

