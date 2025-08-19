package com.example.base.ui.utils

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow

object Utils {
    fun floatToDp(context: Context, value: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, value, context.resources.displayMetrics
        ).toInt()
    }

    fun showPopupCenter(view: View) {
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = false
        val popupWindow = PopupWindow(view, width, height, focusable)
        popupWindow.isOutsideTouchable = false
        popupWindow.elevation = 10f
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }
}