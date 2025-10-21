package com.example.base.ui.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinearLayoutManagerPercent(
    val percent: Float,
    context: Context,
    orientation: Int,
    revertLayout: Boolean
) : LinearLayoutManager(context, orientation, revertLayout) {


    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        lp?.width = (width * percent).toInt()
        return true
    }

    override fun calculateExtraLayoutSpace(state: RecyclerView.State, extraLayoutSpace: IntArray) {
        // Không layout thêm ngoài màn hình
        extraLayoutSpace[0] = 0; // trước màn hình
        extraLayoutSpace[1] = 0; // sau màn hình
    }
}