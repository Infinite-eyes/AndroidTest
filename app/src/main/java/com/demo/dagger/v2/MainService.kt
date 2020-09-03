package com.demo.dagger.v2

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/21 7:39 PM
 **/
interface MainService{
    fun getMainInfo():String
}
class MainServiceImpl: MainService{
    override fun getMainInfo(): String {
      return "This is main info"
    }
}