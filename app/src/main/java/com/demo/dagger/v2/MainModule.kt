package com.demo.dagger.v2

import dagger.Module
import dagger.Provides

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/21 7:41 PM
 **/
@Module
class MainModule {

    @Provides
    fun provideMainService(): MainService {
        return MainServiceImpl()
    }

}