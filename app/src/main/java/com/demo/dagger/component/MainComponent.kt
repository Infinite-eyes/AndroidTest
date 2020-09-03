package com.demo.dagger.component

import com.demo.dagger.DaggerActivity
import com.demo.dagger.moudule.MainModule
import dagger.Component

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/21 1:27 PM
 **/
//@Component(modules = {MainModule.class})
@Component(modules =  [(MainModule::class)])
 interface MainComponent {
    fun inject(activity: DaggerActivity);
}