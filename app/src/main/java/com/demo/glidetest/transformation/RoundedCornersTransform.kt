package com.demo.glidetest.transformation

import android.content.Context
import android.graphics.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import java.security.MessageDigest


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/6/12 12:08 PM
 **/
class RoundedCornersTransform(context: Context?, radius: Float) : Transformation<Bitmap?> {

    private var mBitmapPool: BitmapPool? = null

    private var radius = 0f

    private var isLeftTop = false;
    private var isRightTop = false;
    private var isLeftBottom = false;
    private var isRightBotoom = false;


    fun setNeedCorner(
        leftTop: Boolean,
        rightTop: Boolean,
        leftBottom: Boolean,
        rightBottom: Boolean
    ) {
        isLeftTop = leftTop
        isRightTop = rightTop
        isLeftBottom = leftBottom
        isRightBotoom = rightBottom
    }

    fun RoundedCornersTransform(
        context: Context?,
        radius: Float
    ) {
        mBitmapPool = Glide.get(context!!).bitmapPool
        this.radius = radius
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transform(
        context: Context,
        resource: Resource<Bitmap?>,
        outWidth: Int,
        outHeight: Int
    ): Resource<Bitmap?> {

        val source: Bitmap = resource.get()
        var finalWidth: Int
        var finalHeight: Int
        //输出目标的宽高或高宽比例
        //输出目标的宽高或高宽比例
        var scale: Float
        if (outWidth > outHeight) { //如果 输出宽度 > 输出高度 求高宽比
            scale = outHeight.toFloat() / outWidth.toFloat()
            finalWidth = source.width
            //固定原图宽度,求最终高度
            finalHeight = (source.width.toFloat() * scale).toInt()
            if (finalHeight > source.height) { //如果 求出的最终高度 > 原图高度 求宽高比
                scale = outWidth.toFloat() / outHeight.toFloat()
                finalHeight = source.height
                //固定原图高度,求最终宽度
                finalWidth = (source.height.toFloat() * scale).toInt()
            }
        } else if (outWidth < outHeight) { //如果 输出宽度 < 输出高度 求宽高比
            scale = outWidth.toFloat() / outHeight.toFloat()
            finalHeight = source.height
            //固定原图高度,求最终宽度
            finalWidth = (source.height.toFloat() * scale).toInt()
            if (finalWidth > source.width) { //如果 求出的最终宽度 > 原图宽度 求高宽比
                scale = outHeight.toFloat() / outWidth.toFloat()
                finalWidth = source.width
                finalHeight = (source.width.toFloat() * scale).toInt()
            }
        } else { //如果 输出宽度=输出高度
            finalHeight = source.height
            finalWidth = finalHeight
        }

        //修正圆角
        //修正圆角
        radius *= finalHeight.toFloat() / outHeight.toFloat()
        var outBitmap: Bitmap = mBitmapPool!![finalWidth, finalHeight, Bitmap.Config.ARGB_8888]
        if (outBitmap == null) {
            outBitmap = Bitmap.createBitmap(finalWidth, finalHeight, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(outBitmap)
        val paint = Paint()
        //关联画笔绘制的原图bitmap
        //关联画笔绘制的原图bitmap
        val shader =
            BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        //计算中心位置,进行偏移
        //计算中心位置,进行偏移
        val width = (source.width - finalWidth) / 2
        val height = (source.height - finalHeight) / 2
        if (width != 0 || height != 0) {
            val matrix = Matrix()
            matrix.setTranslate((-width).toFloat(), (-height).toFloat())
            shader.setLocalMatrix(matrix)
        }

        paint.setShader(shader)
        paint.setAntiAlias(true)
        val rectF =
            RectF(0.0f, 0.0f, canvas.getWidth() as Float, canvas.getHeight() as Float)
        //先绘制圆角矩形
        //先绘制圆角矩形
        canvas.drawRoundRect(rectF, radius, radius, paint)

        //左上角圆角
        //左上角圆角
        if (!isLeftTop) {
            canvas.drawRect(0f, 0f, radius, radius, paint)
        }
        //右上角圆角
        //右上角圆角
        if (!isRightTop) {
            canvas.drawRect(canvas.getWidth() - radius, 0f, canvas.width.toFloat(), radius, paint)
        }
        //左下角圆角
        //左下角圆角
        if (!isLeftBottom) {
            canvas.drawRect(0f, canvas.getHeight() - radius, radius, canvas.height.toFloat(), paint)
        }
        //右下角圆角
        //右下角圆角
        if (!isRightBotoom) {
            canvas.drawRect(
                canvas.getWidth() - radius,
                canvas.getHeight() - radius,
                canvas.width.toFloat(),
                canvas.height.toFloat(),
                paint
            )
        }

        return BitmapResource.obtain(outBitmap, mBitmapPool!!)!!

    }

}