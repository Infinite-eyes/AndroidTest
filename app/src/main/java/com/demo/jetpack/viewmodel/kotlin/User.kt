//package com.demo.jetpack.viewmodel.kotlin
//
//import android.os.Parcel
//import android.os.Parcelable
//
///**
// * @author chenweiming
// * @version 1.0
// * @mail cwm930215@gmail.com
// * @since 2020/7/10 7:11 PM
// **/
//class User() : Parcelable {
//
//
//    constructor(parcel: Parcel) : this() {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<User> {
//        override fun createFromParcel(parcel: Parcel): User {
//            return User(parcel)
//        }
//
//        override fun newArray(size: Int): Array<User?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//}