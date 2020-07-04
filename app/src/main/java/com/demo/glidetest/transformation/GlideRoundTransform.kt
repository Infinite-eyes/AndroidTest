package com.demo.glidetest.transformation

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.security.MessageDigest


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/3 3:29 PM
 **/
//class GlideRoundTransform @JvmOverloads constructor(
//    context: Context,
//    dp: Int = 4
//) :
//    BitmapTransformation(context) {
//    protected override fun transform(
//        pool: BitmapPool,
//        toTransform: Bitmap,
//        outWidth: Int,
//        outHeight: Int
//    ): Bitmap? {
//        val bitmap = TransformationUtils.centerCrop(
//            pool,
//            toTransform!!, outWidth, outHeight
//        )
//        return roundCrop(pool, bitmap)
//    }
//
//    fun getId(): String {
//        return javaClass.name + Math.round(radius)
//    }
//
//    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}
//
//    companion object {
//        private const var radius = 0f
//        private fun roundCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
//            if (source == null) return null
//            var result: Bitmap? =
//                pool[source.width, source.height, Bitmap.Config.ARGB_8888]
//            if (result == null) {
//                result = Bitmap.createBitmap(
//                    source.width,
//                    source.height,
//                    Bitmap.Config.ARGB_8888
//                )
//            }
//            val canvas = Canvas(result!!)
//            val paint = Paint()
//            paint.shader = BitmapShader(
//                source,
//                BitmapShader.TileMode.CLAMP,
//                BitmapShader.TileMode.CLAMP
//            )
//            paint.isAntiAlias = true
//            val rectF = RectF(
//                0f, 0f,
//                source.width.toFloat(),
//                source.height.toFloat()
//            )
//            canvas.drawRoundRect(
//                rectF,
//                radius,
//                radius,
//                paint
//            )
//            return result
//        }
//    }
//
//    init {
//        radius = Resources.getSystem().displayMetrics.density * dp
//    }
//}