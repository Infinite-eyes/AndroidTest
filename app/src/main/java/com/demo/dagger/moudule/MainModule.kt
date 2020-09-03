package com.demo.dagger.moudule

import com.demo.dagger.A
import dagger.Module
import dagger.Provides

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/21 1:25 PM
 **/
@Module
class MainModule {

    @Provides
    fun providerA(): A {
        return A()
    }

}