package com.example.base.ui.layout

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams

class DotTabView(context: Context) : FrameLayout(context) {

    private val dot = View(context)
    private var animator: ValueAnimator? = null

    private val dotSizeWidth = dp(12)
    private val dotSizeWHeight = dp(8)
    private val pillWidth = dp(28)
    private val pillHeight = dp(8)
    private val duration = 200L

    init {
        dot.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = dotSizeWidth / 2f
            setColor(Color.parseColor("#D1D5DB"))
        }
        addView(dot, LayoutParams(dotSizeWidth, dotSizeWidth))
    }

    fun setSelectedState(selected: Boolean, animate: Boolean) {
        val targetW = if (selected) pillWidth else dotSizeWidth
        val targetH = if (selected) pillHeight else dotSizeWHeight
        val dotBackground = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = dotSizeWidth / 2f
            setColor(if (selected) Color.parseColor("#1F2937") else Color.parseColor("#D1D5DB"))
        }

        animator?.cancel()
        if (animate) {
            val startW = dot.layoutParams.width
            animator = ValueAnimator.ofInt(startW, targetW).apply {
                duration = this@DotTabView.duration
                startDelay = 0L
                interpolator = DecelerateInterpolator()
                addUpdateListener {
                    val w = it.animatedValue as Int
                    dot.updateLayoutParams<LayoutParams> {
                        width = w
                        height = targetH
                    }
                    dot.background = dotBackground
                }
                start()
            }
        } else {
            dot.updateLayoutParams<LayoutParams> {
                width = targetW
                height = targetH
            }
            dot.background = dotBackground
        }
    }

    private fun dp(v: Int): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, v.toFloat(), resources.displayMetrics
        ).toInt()
}
