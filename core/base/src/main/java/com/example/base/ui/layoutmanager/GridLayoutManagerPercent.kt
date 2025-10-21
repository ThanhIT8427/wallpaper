package com.example.base.ui.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridLayoutManagerPercent(
    val percent: Float,
    context: Context,
    spanCount: Int,
    orientation: Int,
    revertLayout: Boolean
) : GridLayoutManager(context, spanCount, orientation, revertLayout) {


    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        lp?.height = (height * percent).toInt()
        return true
    }
}