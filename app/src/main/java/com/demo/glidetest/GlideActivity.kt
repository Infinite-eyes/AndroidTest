package com.demo.glidetest

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.demo.androidtest.R
import com.demo.glidetest.module.GlideApp
import com.demo.glidetest.transformation.RoundedCornersTransform
import com.demo.utils.dip2px
import java.io.File


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/6/12 11:44 AM
 **/
class GlideActivity : AppCompatActivity() {


    lateinit var iv: ImageView
    var url =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594011971181&di=6b3bf4fbef9a4686b2a7cb22e89a1c6b&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201311%2F08%2F20131108162842_d4L2d.jpeg"
//            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1983299352,541571503&fm=26&gp=0.jpg";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.glide_test_activity)
        iv = findViewById<ImageView>(R.id.iv_1);

//            "http://huabeifile.grayoss.com/data/20200701/1593594158744665741429.png"

        //work

//        var roundedCorners = RoundedCorners(dip2px(this, 35f));
//        var options = RequestOptions.bitmapTransform(roundedCorners).override(
//            this.widthPixels, dip2px(this, 100f)
//        );
//        Glide.with(this).load(url).apply(options).into(findViewById<ImageView>(R.id.iv_1));

        //not work
//        var transform =
//            RoundedCornersTransform(this, 100f);
//        transform.setNeedCorner(true, false, true, false);
//        var options = RequestOptions().placeholder(R.color.black).transform(transform);
//        Glide.with(this).load(url).apply(options).into(findViewById<ImageView>(R.id.iv_1));
//        Glide.with(this).asBitmap().load(url).apply(options).into(findViewById<ImageView>(R.id.iv_1));

        //work
//        Glide.with(this).load(url)
//            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 5)))
//            .into(findViewById<ImageView>(R.id.iv_1))

        //work
//        Glide.with(this).load(url)
//            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 5)))
//            .into(findViewById<ImageView>(R.id.iv_1))


//        Glide.with(this).load(url).override(this.widthPixels, dip2px(this, 100f))
//            .transition(
//                FitCenter(this),
//                GlideRoundTransform(mContext, 4)
//            )
//            .apply(
//                RequestOptions.bitmapTransform(
//                    RoundedCornersTransformation(
//                        dip2px(this, 50f),
//                        0,
//                        RoundedCornersTransformation.CornerType.ALL
//                    )
//                )
//            )
//            .into(findViewById<ImageView>(R.id.iv_1))

        //work
//        val multi = MultiTransformation<Bitmap>(
//            BlurTransformation(25),
//            RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.TOP)
//        );

//        Glide.with(this).load(url)
//            .apply(RequestOptions.bitmapTransform(multi))
//            .into(findViewById<ImageView>(R.id.iv_1));

//     //   默认变换
//        Glide.with(this)
//            .load(url)
//            .fitCenter()
//            .into(findViewById<ImageView>(R.id.iv_1));

//        or
//        val options = RequestOptions()
//        options.centerCrop()
//
//        Glide.with(fragment)
//            .load(url)
//            .apply(options)
//            .into(imageView)
//
//        Glide.with(this)
//            .load(url)
//            .transform(
//                FitCenter(),
//                RoundedCornersTransform(this,50f)
//            )
//            .into(findViewById<ImageView>(R.id.iv_1))

        val roundedCorners = RoundedCorners(dip2px(this, 5f))
        val options = RequestOptions.bitmapTransform(roundedCorners)
            .override(dip2px(this, 360f), dip2px(this, 100f))

        var offset = dip2px(this, 50f)


//        Glide.with(this)
//            .load(url)
////            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
////            .override(this.widthPixels, dip2px(this, 100f))
////            .apply(options)
////        CropTransformation(this.widthPixels, dip2px(this, 100f))
////            .transform(RoundedCorners(dip2px(this, 50f)))
////            .transform(RoundedCornersTransform(this, 50f))
////            .transform(CenterCrop(), RoundedCorners(dip2px(this, 50f)))
////            .transform(GranularRoundedCorners(offset, offset, offset, offset))
////            .transform(BitmapTransitionOptions(), RoundedCorners(dip2px(this, 50f)))
////            .transform(RoundedCornersTransform(this, 50f))
//            .transform(FitCenter(), RoundedCornersTransformation(offset, 0))
////            .transform(CropCircleWithBorderTransformation(), RoundedCornersTransformation(offset, 0))
//            .into(findViewById(R.id.iv_1))
//            .waitForLayout()

//        testBaseTransformation()
        testTransformation()
//        testTransformation2()
//        testTargets();


    }


    fun testBaseTransformation() {
//        Glide.with(this)
//            .load(url)
//            .fitCenter()
//            .into(findViewById(R.id.iv_1))
//            .waitForLayout()

        Glide.with(this)
            .load(url)
            .transform(FitCenter(), CircleCrop())
            .into(findViewById(R.id.iv_1))


        Glide.with(this)
            .load(url)
//            .fitCenter()
            .centerCrop()
//               .circleCrop()
            .into(findViewById(R.id.iv_2))
            .waitForLayout()


        Glide.with(this)
            .load(url)
            //            .fitCenter()
//            .centerCrop()
            .circleCrop()
            .into(findViewById(R.id.iv_3));

        Glide.with(this)
            .load(url)
            .fitCenter()
//            .centerCrop()
            .circleCrop()
            .into(findViewById(R.id.iv_4));

    }

    fun testTransformation() {

//        var roundedCorners = RoundedCorners(dip2px(this, 35f));
//        var options = RequestOptions.bitmapTransform(roundedCorners).override(
//            this.widthPixels, dip2px(this, 100f)
//        );

        val transform =
            RoundedCornersTransform(
                this,
                dip2px(this, 100f).toFloat()
            )
        transform.setNeedCorner(true, true, true, true)

        Glide.with(this)
//            .asBitmap()

            .load(url)
//            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//            .apply(options)
//            .transform(RoundedCornersTransformation(500, 100))
//            .transform(GlideRoundTransform(this, 50))
            .transform(transform)
            .into(findViewById(R.id.iv_1))
    }

    fun testTransformation2() {

//        val transform =
//            RoundedCornersTransform2(
//                this,
//                dip2px(this, 1000f).toFloat()
//            )
//        transform.setNeedCorner(true, true, true, true)
//
//        Glide.with(this)
//            .load(url)
////            .transform(transform)
//            .transform(RoundedCornersTransformation(dip2px(this, 500f), 0))
//            .into(findViewById(R.id.iv_1))
    }


    fun testGlideApp() {
        GlideApp.with(this)
            .load(url)
            .applyAvatarImage()
            .into(iv);
    }


    fun testTargets() {


        val target: CustomTarget<Bitmap?> = object : CustomTarget<Bitmap?>() {
//            fun onResourceReady(@NonNull resource: Bitmap?, @Nullable transition: Transition<in Bitmap?>) { //回调内容
////                imageView.setImageBitmap(resource)
//            }

            override fun onLoadCleared(@Nullable placeholder: Drawable?) { //这个方法在target被回收时调用，如果在除了imageView以外的地方引用了imageView中的bitmap，在这里清除引用以避免崩溃
            }

            //
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                iv.setImageBitmap(resource)
            }
        }

        GlideApp.with(this)
            .asBitmap()
            .load(url)
//            .override(this.widthPixels, dip2px(this, 100f))
//            .transform(RoundedCorners(dip2px(this, 200f)))
//            .into(BitmapImageViewTarget(iv))
            .into(target)
//


    }


    fun loadNetImg(url: String, view: ImageView) {
        Glide.with(this).load(url).into(view).waitForLayout()
    }

    fun loadResImg(view: ImageView) {
        Glide.with(this).load(R.mipmap.ic_launcher).into(view)
    }

    fun loadLocalImg(view: ImageView) {

        //v1
//        val sdCard = Environment.getExternalStorageDirectory()
//        val directory_pictures = File(sdCard, "Pictures")
//        var uri = Uri.fromFile(directory_pictures)

//        todo  v2
        var path = Environment.getExternalStorageDirectory().absolutePath + "/aa";
        var file = File(path)
        var uri = Uri.fromFile(file)

//        Glide.with(this).load(uri).into(view)
    }


}