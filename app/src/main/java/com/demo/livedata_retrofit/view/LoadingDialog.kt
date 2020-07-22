package com.demo.livedata_retrofit.view

import android.app.Dialog
import android.content.Context
import android.view.KeyEvent
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.demo.androidtest.R

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/22 3:27 PM
 **/
class LoadingDialog(context: Context?) :
    Dialog(context!!, R.style.http_loading_dialog) {
    var isTouchDismiss = false

    /**
     * 点击空白区域是否可关闭
     *
     */
    fun isTouchDismiss(isTouchDismiss: Boolean) {
        this.isTouchDismiss = isTouchDismiss
        setCanceledOnTouchOutside(isTouchDismiss)
    }

    /**
     * 点击返回按钮是否关闭
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (!isTouchDismiss && event.keyCode == KeyEvent.KEYCODE_BACK) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    init {
        setContentView(R.layout.http_loading_dialog)
        val gifView = findViewById<ImageView>(R.id.iv)
        Glide.with(context!!).load(R.drawable.webview_loading).into(gifView)
        setCanceledOnTouchOutside(isTouchDismiss)
    }
}