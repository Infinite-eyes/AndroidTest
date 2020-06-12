package com.demo.glidetest

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import com.demo.androidtest.R
import com.demo.glidetest.transformation.RoundedCornersTransform
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

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
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1983299352,541571503&fm=26&gp=0.jpg";

        //work
//        var roundedCorners = RoundedCorners(10);
//        var options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
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

        Glide.with(this).load(url)
            .apply(RequestOptions.bitmapTransform(  RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.TOP)))
            .into(findViewById<ImageView>(R.id.iv_1))

        //work
//        val multi = MultiTransformation<Bitmap>(
//            BlurTransformation(25),
//            RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.TOP)
//        );

//        Glide.with(this).load(url)
//            .apply(RequestOptions.bitmapTransform(multi))
//            .into(findViewById<ImageView>(R.id.iv_1));


    }


}