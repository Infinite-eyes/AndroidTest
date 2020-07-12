package com.demo.jetpack.viewmodel.kotlin.viewodelwithlivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/12 10:26 PM
 **/
class ViewModelWithLiveData : ViewModel() {

      var LikedNumber: MutableLiveData<Int> = MutableLiveData(0)



    fun addLiedNumber(n: Int) {
        LikedNumber.value = LikedNumber.value?.plus(n)
    }

}