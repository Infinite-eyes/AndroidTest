package com.demo.glidetest

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.demo.androidtest.R
import com.demo.utils.dip2px
import java.io.File


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/6/12 11:44 AM
 **/
class GlideActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.glide_test_activity)


        var url =
//            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1983299352,541571503&fm=26&gp=0.jpg";
            "http://huabeifile.grayoss.com/data/20200630/1593496841441612501034.png";

//            "http://huabeifile.grayoss.com/data/20200701/1593594158744665741429.png"

        //work

//        var roundedCorners = RoundedCorners(dip2px(this, 35f));
//
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

//        val requestBuilder: RequestBuilder<Drawable> =




        var offset = dip2px(this, 50f)

//        Glide.with(this)
//            .asDrawable()
//            .load(url)
//            .apply(options)
//            .transform(RoundedCornersTransformation(offset, 0))
//            .into(findViewById(R.id.iv_1))


//        Glide.with(this).load(url).asBitmap().centerCrop()
//            .into(object : BitmapImageViewTarget(findViewById(R.id.iv_1)) {
//                override fun setResource(resource: Bitmap?) {
//                    val circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(this.getResources(), resource)
//                    circularBitmapDrawable.isCircular = true
//                    findViewById(R.id.iv_1).setImageDrawable(circularBitmapDrawable)
//                }
//            })

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
//
////                        .transition(DrawableTransitionOptions.withCrossFade())
//
//
//            .transform(FitCenter(), RoundedCornersTransformation(offset, 0))
////            .transform(CropCircleWithBorderTransformation(), RoundedCornersTransformation(offset, 0))
//            .into(findViewById(R.id.iv_1))
//            .waitForLayout()


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