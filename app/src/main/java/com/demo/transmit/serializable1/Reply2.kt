package com.demo.transmit.serializable1

import java.io.Serializable

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2021/4/26 23:49
 **/

open class Reply2<T> : Serializable {
    open fun reply(reply: T?){

    }
}