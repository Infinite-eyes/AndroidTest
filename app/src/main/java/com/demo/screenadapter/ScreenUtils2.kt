package com.demo.screenadapter

import android.app.Activity
import android.content.res.Resources

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/8/28 8:51 PM
 **/
object ScreenUtils2 {

    fun adapterScreen(activity: Activity, targetDP: Int, isVertical: Boolean) {

        val systemDM = Resources.getSystem().displayMetrics
        val appDM = activity.application.resources.displayMetrics

        val activityDM = activity.resources.displayMetrics

        if (isVertical) {
            activityDM.density = activityDM.heightPixels / targetDP.toFloat()
        } else {
            activityDM.density = activityDM.widthPixels / targetDP.toFloat()
        }

        activityDM.scaledDensity = activityDM.density * (systemDM.scaledDensity / systemDM.density)

        activityDM.densityDpi = (160 * activityDM.density).toInt()
    }

    fun resetScreen(activity: Activity) {

        val systemDM = Resources.getSystem().displayMetrics
        val appDM = activity.application.resources.displayMetrics
        val activityDM = activity.resources.displayMetrics

        activityDM.density = systemDM.density
        activityDM.scaledDensity = systemDM.scaledDensity
        activityDM.densityDpi = systemDM.densityDpi

        appDM.density = systemDM.density
        appDM.scaledDensity = systemDM.scaledDensity
        appDM.densityDpi = systemDM.densityDpi
    }


}