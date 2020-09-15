package com.demo.material.bottomnavigationview

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.demo.androidtest.R
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/9/14 6:24 PM
 **/
class BottomNavigationViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_navigation_view_activity)
        var navigation = findViewById<BottomNavigationView>(R.id.navView)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
           override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.home -> return true
                    R.id.blog -> return true
                    R.id.search -> return true
                    R.id.project -> return true
                    R.id.profile -> return true
                }
                return false
            }
        }

}