package com.demo.utils

import android.content.Context
import android.util.TypedValue

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/3 10:40 AM
 **/


fun dip2px(context: Context, dp: Float): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    context.resources.displayMetrics
).toInt()

val Context.widthPixels: Int get() = resources.displayMetrics.widthPixels
val Context.heightPixels: Int get() = resources.displayMetrics.heightPixels


