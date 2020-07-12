package com.demo.jetpack.viewmodel.kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.demo.androidtest.R
import kotlinx.android.synthetic.main.jepack_base_viewmodel_activity.*

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/12 9:53 PM
 **/
class BaseViewModelActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jepack_base_viewmodel_activity)

        val myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        //旋转屏幕数据不丢失
        tv_content.text = myViewModel.number.toString();


        btn1.setOnClickListener {
            myViewModel.number += 1
            tv_content.text = myViewModel.number.toString()

        }
        btn2.setOnClickListener {
            myViewModel.number += 2
            tv_content.text = myViewModel.number.toString()
        }


    }
}
