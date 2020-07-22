package com.demo.livedata_retrofit.model

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/21 6:22 PM
 **/
data class BannerVO(
    var id: Int,
    var title: String,
    var desc: String,
    var type: Int,
    var url: String,
    var imagePath: String
)