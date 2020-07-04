package com.demo.glidetest.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/4 1:10 PM
 **/
@GlideModule
class GlideConfigModule : AppGlideModule() {


    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        super.registerComponents(context, glide, registry)
    }


    override fun applyOptions(context: Context, builder: GlideBuilder) {
//        super.applyOptions(context, builder)
    }


}