package com.demo.livedata_retrofit

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/21 1:29 PM
 **/
class ApiResponse<T>(
    var data: T?,
    var errorCode: Int,
    var errorMsg: String
)