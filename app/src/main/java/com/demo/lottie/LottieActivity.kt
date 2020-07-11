package com.demo.lottie

import android.app.Activity
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.demo.androidtest.R

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/11 5:17 PM
 **/
class LottieActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lottie_activity)

        val lav = findViewById<LottieAnimationView>(R.id.lav)
        val img = findViewById<ImageView>(R.id.img)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            img.setOnClickListener {
//                (img.drawable as AnimatedVectorDrawable).start()
//            }
            lav.setOnClickListener {
                lav.playAnimation()
            }
        }

    }

}