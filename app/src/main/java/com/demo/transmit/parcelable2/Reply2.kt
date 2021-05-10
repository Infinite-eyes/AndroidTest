package com.demo.transmit.parcelable2

import android.os.Parcel
import android.os.Parcelable
import android.util.Log

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2021/4/26 23:49
 **/

open class Reply2<T>() : Parcelable {

    constructor(parcel: Parcel) : this() {
    }

    open fun reply(reply: T?){
        Log.e("TAG","normal")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Reply2<Any>> {
        override fun createFromParcel(parcel: Parcel): Reply2<Any> {
            return Reply2(parcel)
        }

        override fun newArray(size: Int): Array<Reply2<Any>?> {
            return arrayOfNulls(size)
        }
    }
}