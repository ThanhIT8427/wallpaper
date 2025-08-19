package com.example.base.ui.textview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.base.R

const val NO_COLOR = Color.WHITE
const val DEFAULT_STROKE_WIDTH = 0f

class RoundedLinearLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val rectF = RectF()
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private var cornerRadius = 50f

    init {
        setWillNotDraw(false)
        context.theme.obtainStyledAttributes(attrs, R.styleable.RoundedLinearLayout, 0, 0).apply {
            val color =
                getColor(R.styleable.RoundedLinearLayout_roundBackground, NO_COLOR)
            backgroundPaint.color = color
            val radius = getFloat(R.styleable.RoundedLinearLayout_radius, 50f)
            cornerRadius = radius
            val strokeColor = getColor(R.styleable.RoundedLinearLayout_roundStrokeColor, NO_COLOR)
            val strokeWidth = getFloat(R.styleable.RoundedLinearLayout_roundStrokeWidth, DEFAULT_STROKE_WIDTH)
            strokePaint.apply {
                this.strokeWidth = strokeWidth
                this.color = strokeColor
            }
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        rectF.set(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, backgroundPaint)
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, strokePaint)
        super.onDraw(canvas)
    }

    fun setRadius(radius: Float) {
        cornerRadius = radius
        invalidate()
    }

    fun setBgColor(color: Int) {
        backgroundPaint.color = color
        invalidate()
    }
}