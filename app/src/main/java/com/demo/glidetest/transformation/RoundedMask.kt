package com.demo.glidetest.transformation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import androidx.annotation.NonNull
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.bumptech.glide.util.Util
import java.nio.ByteBuffer
import java.security.MessageDigest


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/3 4:06 PM
 **/
private class RoundedMask internal constructor(d: GradientDrawable) : BitmapTransformation() {

    val drawable: GradientDrawable

    override fun transform(
        @NonNull pool: BitmapPool, @NonNull toTransform: Bitmap, outWidth: Int, outHeight: Int
    ): Bitmap {
        val bitmap =
            TransformationUtils.roundedCorners(
                pool!!,
                toTransform!!,
                drawable.cornerRadius.toInt()
            )
        val canvas = Canvas(bitmap)
        val width = bitmap.width
        val height = bitmap.height
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        canvas.setBitmap(null)
        return bitmap
    }

    override fun hashCode(): Int {
        return Util.hashCode(RoundedMask::class.java.name.hashCode(), drawable.hashCode())
    }

    override fun updateDiskCacheKey(@NonNull messageDigest: MessageDigest) {
        messageDigest.update(RoundedMask::class.java.name.toByteArray(charset(STRING_CHARSET_NAME)))
        run {
            val radiusData: ByteArray =
                ByteBuffer.allocate(4).putInt(drawable.hashCode()).array()
            messageDigest.update(radiusData)
        }
    }

    init {
        drawable = d
    }
}
