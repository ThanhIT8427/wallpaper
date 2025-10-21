package com.example.base.ui.helper

import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2

class SliderPageTransformer(
    private val pagerMargin: Int,
    private val pagerOffset: Int
): ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val viewPager2 = page.parent.parent as? ViewPager2
        viewPager2?.let {
            val offset = position * -(2 * pagerOffset + pagerMargin)
            val viewDirection = ViewCompat.getLayoutDirection(it) == ViewCompat.LAYOUT_DIRECTION_RTL
            if(it.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (viewDirection) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
        } ?: let {
            Log.d("Thanh123","ViewPager2 null")
        }
    }
}