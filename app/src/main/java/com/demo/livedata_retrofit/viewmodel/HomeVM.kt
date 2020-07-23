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

    private val page = MutableLiveData<Int>()
    val refreshing = MutableLiveData<Boolean>()
    val moreLoading = MutableLiveData<Boolean>()
    val hasMore = MutableLiveData<Boolean>()

    private val articleList = Transformations.switchMap(page) {
        api.articleList(it)
    }

    val articlePage = Transformations.map(articleList) {
        refreshing.value = false
        moreLoading.value = false
        hasMore.value = !(it?.data?.over ?: false)
        it.data
    }

    fun loadMore() {
        page.value = (page.value ?: 0) + 1
        moreLoading.value = true
    }

    fun refresh() {
        loadBanner()
        page.value = 0
        refreshing.value = true
    }


    private val bannerList: LiveData<ApiResponse<List<BannerVO>>> =
        Transformations.switchMap(refreshTrigger) {
            api.bannerList()
        }

    val banners: LiveData<List<BannerVO>> = Transformations.map(bannerList) {
        loading.value = false
        it.data ?: ArrayList()
    }


    fun loadBanner() {
        refreshTrigger.value = true
        loading.value = true
    }


    fun attachLoading(otherLoadingState: MutableLiveData<Boolean>) {
        loading.observeForever {
            otherLoadingState.value = it
        }
    }


}