package com.demo.viewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.demo.androidtest.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.workmanager_activity.*

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/7 12:09 AM
 **/
class ViewModeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewmode_activity)
//        val myViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        ).get(MyViewModel::class.java)

        val myViewModel by viewModels<MyViewModel>()


        myViewModel.number.observe(
            this, Observer {
                tv.text = it.toString()
            }
        )

        button.setOnClickListener{
            myViewModel.addOne()
        }


    }

}