package com.demo.recycleview

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/6/16 7:39 PM
 **/
class StaggeredDividerItemDecoration(context: Context, interval: Int) : ItemDecoration() {

    private val context: Context
    private val interval: Int

    init {
        this.context = context
        this.interval = interval
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val interval = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, interval.toFloat(), context.resources.displayMetrics
        ).toInt()
        // 中间间隔


        if (position < 2) {
            if (position % 2 == 0) {
                outRect.left = 0
            } else { // item为奇数位，设置其左间隔为5dp
                outRect.left = interval
            }
        } else {
            if (position % 2 == 0) {
                outRect.left = interval
            } else { // item为奇数位，设置其左间隔为5dp
                outRect.left = 0
            }
        }

//
//
//        if (position % 2 == 0 && position != 2) {
//            outRect.left = 0
//        } else { // item为奇数位，设置其左间隔为5dp
//            outRect.left = interval
//        }


        // 下方间隔
        outRect.bottom = interval
    }


}