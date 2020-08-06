package com.demo.viewmodel

import android.app.Application
import androidx.lifecycle.*

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/7 12:07 AM
 **/
class MyViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    //class MyViewModel(private val savedStateHandle: SavedStateHandle, application: Application) : AndroidViewModel(application) {
    private val _number = MutableLiveData<Int>().also {
        if (!savedStateHandle.contains("number")) {
            savedStateHandle.set("number", 0)
        }
        it.value = savedStateHandle.get("number")
    }

    val number: LiveData<Int> = _number

    fun addOne() {
//        getApplication<Application>
        _number.value = _number.value?.plus(1)
        savedStateHandle.set("number", _number.value)
    }


}