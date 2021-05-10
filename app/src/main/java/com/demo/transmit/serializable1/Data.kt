package com.demo.transmit.serializable1

import java.io.Serializable

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2021/4/26 13:58
 **/
data class Data(
    var aa: String,
    var bb: String,
    var cc: Action
):Serializable