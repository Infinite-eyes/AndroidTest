package com.demo.glidetest.extension

import android.R
import androidx.annotation.NonNull
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.BaseRequestOptions


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/4 1:21 PM
 **/
@GlideExtension
object MyGlideExtension {
    /**
     * 默认占位图
     */
//    private val ICON_DEFAULT_PLACEHOLDER: Int = R.drawable.placeholder


//    companion object {
        @NonNull
        @JvmStatic
        @GlideOption
        fun applyAvatarImage(options: BaseRequestOptions<*>): BaseRequestOptions<*> {
            return options
                .centerCrop()
//            .placeholder(ICON_DEFAULT_PLACEHOLDER)
//            .error(ICON_DEFAULT_PLACEHOLDER)
                .circleCrop()
                .format(DecodeFormat.PREFER_RGB_565)
        }
//    }

}