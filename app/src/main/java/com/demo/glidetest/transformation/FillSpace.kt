package com.demo.glidetest.transformation

import android.graphics.Bitmap
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.io.UnsupportedEncodingException
import java.security.MessageDigest


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/3 1:30 PM
 **/
class FillSpace : BitmapTransformation() {
    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return if (toTransform.width == outWidth && toTransform.height == outHeight) {
            toTransform
        } else Bitmap.createScaledBitmap(toTransform, outWidth, outHeight,  /*filter=*/true)
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        try {
            messageDigest.update(ID.toByteArray(charset(Key.STRING_CHARSET_NAME)))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val ID = "com.demo.glidetest.transformation.FillSpace2"
    }

    override fun equals(o: Any?): Boolean {
        if (o is RoundedCorners) {
//            val other = o
//            return roundingRadius === other.roundingRadius
            return true
        }
        return false
    }
}