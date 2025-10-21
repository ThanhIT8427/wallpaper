package com.example.base.ui.helper

import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class ScalePagerTransformer(
    private val minScale: Float
) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val realScale = when {
            position < -1 -> {
                minScale
            }

            position <= 0 -> {
                val scale = (1 - abs(position))
                max(scale, minScale)
            }

            position <= 1 -> {
                val scale = (1 - abs(position))
                min(minScale + scale, 1f)
            }

            else -> {
                minScale
            }
        }
        page.scaleY = realScale
    }
}