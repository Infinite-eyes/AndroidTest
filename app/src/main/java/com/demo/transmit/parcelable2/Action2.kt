package com.demo.transmit.parcelable2

import android.os.Parcel
import android.os.Parcelable

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2021/4/27 0:38
 **/
open class Action2() : Parcelable {

    constructor(parcel: Parcel) : this() {
    }

    open fun a(){

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Action2> {
        override fun createFromParcel(parcel: Parcel): Action2 {
            return Action2(parcel)
        }

        override fun newArray(size: Int): Array<Action2?> {
            return arrayOfNulls(size)
        }
    }
}

