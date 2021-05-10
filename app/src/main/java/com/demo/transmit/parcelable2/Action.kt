package com.demo.transmit.parcelable2

import android.os.Parcel
import android.os.Parcelable


/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2021/4/26 17:42
 **/
open class Action(val reply: Reply2<String>? = null) : Parcelable {


    open fun a(){
        reply?.reply("dasdsad")
    }

    constructor(parcel: Parcel) : this(parcel.readParcelable<Reply2<String>>(Reply2::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(reply, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Action> {
        override fun createFromParcel(parcel: Parcel): Action {
            return Action(parcel)
        }

        override fun newArray(size: Int): Array<Action?> {
            return arrayOfNulls(size)
        }
    }


}