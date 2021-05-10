package com.demo.transmit.parcelable2

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2021/4/26 23:43
 **/

interface Reply<T> {
    fun reply(reply: T?)
}
