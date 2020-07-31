package com.demo.launchermodel

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/31 6:05 PM
 **/
class CActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_model_c)

        findViewById<TextView>(R.id.tv_c).setOnClickListener {
            val intent = Intent(this@CActivity, AActivity::class.java)
//            //相当于singleTask
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)


        }
    }


}
