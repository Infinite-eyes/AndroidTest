package com.demo.dagger.v2

import javax.inject.Inject

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/21 7:24 PM
 **/
class MainPresenter @Inject constructor() {
    fun doSomething(): String {
        return "This is result"
    }
}