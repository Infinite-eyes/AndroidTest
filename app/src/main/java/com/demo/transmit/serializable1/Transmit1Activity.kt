package com.demo.transmit.serializable1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2021/4/26 21:49
 **/
class Transmit1Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val reply = object : Reply<String>, Serializable {
            override fun reply(reply: String?) {
//                print(reply)
                Log.e("TAG",reply)
            }
        }

        val reply2 = object : Reply2<String>() {
            override fun reply(reply: String?) {
//                print(reply)
                Log.e("TAG",reply)
            }
        }


        val intent = Intent(this, Transmit2Activity::class.java)
        intent.putExtra("aaa", Data("11", "222", object : Action() {
            override fun a() {
                reply.reply("3213213")
//                reply2.reply("3213213")
            }
        }))
        this.startActivity(intent)
    }


}