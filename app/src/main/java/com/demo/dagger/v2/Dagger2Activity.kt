package com.demo.dagger.v2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R
import javax.inject.Inject

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/21 7:25 PM
 **/
class Dagger2Activity : AppCompatActivity() {

    @Inject
    lateinit var mPresenter: MainPresenter
//    @Inject
//    lateinit var mMainService: MainService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dagger_activity);
        initInjection()


        mPresenter.doSomething()

    }

    private fun initInjection() {
//        Dagge/**/
        DaggerMainComponent.builder().build()
    }

}