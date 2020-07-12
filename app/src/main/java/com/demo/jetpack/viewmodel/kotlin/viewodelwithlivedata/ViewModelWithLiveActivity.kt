package com.demo.jetpack.viewmodel.kotlin.viewodelwithlivedata

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.androidtest.R
import kotlinx.android.synthetic.main.jepack_base_viewmodel_activity.*
import kotlinx.android.synthetic.main.jepack_base_viewmodel_activity.tv_content
import kotlinx.android.synthetic.main.jepack_viewmodel_with_livedata_activity.*

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/12 10:37 PM
 **/
class ViewModelWithLiveActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jepack_viewmodel_with_livedata_activity)

        val viewModelWithLiveData =
            ViewModelProviders.of(this).get(ViewModelWithLiveData::class.java)

        viewModelWithLiveData.LikedNumber.observe(this, Observer {
            tv_content.setText(it.toString())
        })

        iv1.setOnClickListener {
            viewModelWithLiveData.addLiedNumber(1)
        }
        iv2.setOnClickListener {
            viewModelWithLiveData.addLiedNumber(-1)
        }


    }
}