package com.demo.localization

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R
import kotlinx.android.synthetic.main.workmanager_activity.*

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/12 1:48 PM
 **/
//资源多副本 string layout etc
class LocalizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.localization_activity)
        tv_2.text = ""

        button.setOnClickListener(View.OnClickListener {
            tv_2.setText(R.string.Message)
        })
    }

}