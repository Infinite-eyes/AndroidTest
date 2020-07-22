package com.demo.livedata_retrofit.viewmodel

import android.content.Context
import androidx.lifecycle.Observer
import com.demo.livedata_retrofit.view.LoadingDialog

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/22 2:41 PM
 **/
class LoadingObserver(context: Context) : Observer<Boolean> {

    private val dialog = LoadingDialog(context)

    override fun onChanged(show: Boolean?) {
        if (show == null) return
        if (show) {
            dialog.show()
        } else {
            dialog.dismiss()
        }
    }

}