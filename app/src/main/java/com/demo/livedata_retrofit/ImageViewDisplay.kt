package com.demo.livedata_retrofit

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.displayWithUrl(imagePath: String?) {
    Glide.with(this.context)
        .load(imagePath)
        .into(this);
}