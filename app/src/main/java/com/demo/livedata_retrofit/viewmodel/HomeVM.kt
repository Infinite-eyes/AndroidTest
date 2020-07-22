package com.demo.livedata_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.demo.livedata_retrofit.model.WanApi
import com.demo.livedata_retrofit.model.ApiResponse
import com.demo.livedata_retrofit.model.BannerVO

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/21 7:34 PM
 **/
class HomeVM : ViewModel() {

    private val refreshTrigger = MutableLiveData<Boolean>()
    private val api = WanApi.get()
    val loading = MutableLiveData<Boolean>()

    private val bannerList: LiveData<ApiResponse<List<BannerVO>>> =
        Transformations.switchMap(refreshTrigger) {
            api.bannerList()
        }

    val banners: LiveData<List<BannerVO>> = Transformations.map(bannerList) {
        loading.value = false
        it.data ?: ArrayList()
    }


    fun loadData() {
        refreshTrigger.value = true
        loading.value = true
    }


    fun attachLoading(otherLoadingState: MutableLiveData<Boolean>) {
        loading.observeForever {
            otherLoadingState.value = it
        }
    }


}