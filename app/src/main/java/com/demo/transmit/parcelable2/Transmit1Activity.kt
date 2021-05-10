package com.demo.transmit.parcelable2

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2021/4/26 21:49
 **/
class Transmit1Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val reply = object : Reply<String> {
            override fun reply(reply: String?) {
//                print(reply)
                Log.e("TAG",reply)
            }
        }

        val reply2 = object : Reply2<String>(),Parcelable{
            override fun reply(reply: String?) {
//                print(reply)
                Log.e("TAG",reply)
            }
        }
//
        val intent = Intent(this, Transmit2Activity::class.java)


//        intent.putExtra("aaa", AAction("11", "222", object : BaseAction() {
//            override fun a() {
//                reply.reply("3213213")
////                reply2.reply("3213213")
//            }
//        }))

//        val intent = Intent(this, Transmit2Activity::class.java)
//        intent.putExtra("aaa", Data("11", "222", object : Action() {
//            override fun a() {
//                reply.reply("3213213")
////                reply2.reply("3213213")
//            }
//        }))

        intent.putExtra("aaa", Data("11", "222", Action(reply2)))


//        val intent = Intent(this, Transmit2Activity::class.java)
//        intent.putExtra("aaa", Data("11", "222",  Action()))
        this.startActivity(intent)
    }


}