package com.demo.dagger.v2

import dagger.Component

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/21 7:42 PM
 **/
@Component(modules = [(MainModule::class)])
interface MainComponent {
    fun inject(activity: Dagger2Activity)
}
//@Component
//interface MainComponent {
//    fun inject(activity: Dagger2Activity)
//}