package com.example.base.ui.itemdecoration

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OverDrawItemDecoration(val widthOverDrawPercent: Float): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        val widthOverDraw = view.layoutParams.width * widthOverDrawPercent
        Log.d("Thanh123","widthOverDraw: ${view.layoutParams.width}")
        if(itemPosition > 0) {
            outRect.left -= widthOverDraw.toInt()
            view.translationZ = -itemPosition.toFloat()
        } else {
            view.translationZ = -100f
        }
    }
}