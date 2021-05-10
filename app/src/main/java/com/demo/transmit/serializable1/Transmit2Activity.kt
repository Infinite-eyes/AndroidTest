package com.demo.transmit.serializable1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2021/4/26 21:49
 **/
class Transmit2Activity() : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var action: Data = intent.getSerializableExtra("aaa") as Data
        action.cc?.a()
    }

}