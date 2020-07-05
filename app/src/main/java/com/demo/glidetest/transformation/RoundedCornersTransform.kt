package com.demo.glidetest.transformation

import android.content.Context
import android.graphics.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Key
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
class RoundedCornersTransform(context: Context, private var radius: Float) :
        Transformation<Bitmap> {


    companion object {
        const val VERSION = 1
        const val ID = "com.demo.glidetest.transformation.$VERSION"
    }


    var mBitmapPool: BitmapPool = Glide.get(context).bitmapPool

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

    override fun transform(
            context: Context,
            resource: Resource<Bitmap>,
            outWidth: Int,
            outHeight: Int
    ): Resource<Bitmap> {

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
        radius *= finalHeight.toFloat() / outHeight.toFloat()
        var outBitmap: Bitmap = mBitmapPool[finalWidth, finalHeight, Bitmap.Config.ARGB_8888]
        if (outBitmap == null) {
            outBitmap = Bitmap.createBitmap(finalWidth, finalHeight, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(outBitmap)
        val paint = Paint()

        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val matrix = Matrix()
        matrix.setScale(outWidth * 1.0f / source.width, outHeight * 1.0f / source.height)
        shader.setLocalMatrix(matrix)

        paint.setShader(shader)
        paint.setAntiAlias(true)
        val rectF = RectF(0.0f, 0.0f, canvas.width.toFloat(), canvas.height.toFloat())
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

        return BitmapResource.obtain(outBitmap, mBitmapPool) as Resource<Bitmap>

    }

    override fun toString(): String {
        return ("RoundedTransformation(radius=$radius")
    }

    override fun equals(other: Any?): Boolean {
        return other is RoundedCornersTransform && other.radius == this.radius
    }

    override fun hashCode(): Int {
        return (ID.hashCode() + radius * 10000).toInt()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update((ID + radius).toByteArray(Key.CHARSET))
    }


}