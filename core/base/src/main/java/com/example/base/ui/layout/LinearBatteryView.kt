package com.example.base.ui.layout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import kotlin.math.tan

class LinearBatteryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mWidth = 0
    private var mHeight = 0
    private val mCanvasRectF = RectF()

    private val mRectFIcons = RectF()
    private val mPaintIcon = Paint().apply {
        style = Paint.Style.FILL
    }
    private val paddingIconHorizontal = 20f
    private val paddingProgressView = 20f
    private var listIcon: List<Bitmap> = listOf()

    private val mRectFBattery = RectF()
    private val mRectProgress = RectF()
    private val paddingProgress = 10f
    private val strokeColor = "#EC4899".toColorInt()
    private val progressColor = "#D058FF".toColorInt()
    private val desProgressColor = Color.WHITE
    private val progress = 60
    private val countOfLine = 9
    private val mPaintBattery = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = this@LinearBatteryView.strokeColor
    }

    private val mPaintProgress = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = progressColor
    }

    fun setListIcon(listIcon: List<Bitmap>) {
        this.listIcon = listIcon
        invalidate()
    }

    init {
        post {
            (parent as? ViewGroup)?.clipChildren = false
            (parent as? ViewGroup)?.clipToPadding = false
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        mCanvasRectF.set(
            RectF(
                paddingLeft.toFloat(),
                paddingTop.toFloat(),
                mWidth.toFloat() - paddingRight,
                mHeight.toFloat() - paddingBottom
            )
        )

        mRectFIcons.set(
            mCanvasRectF.left,
            mCanvasRectF.top,
            mCanvasRectF.right,
            mCanvasRectF.bottom - mCanvasRectF.height() * 0.7f
        )

        mRectFBattery.set(
            mCanvasRectF.left,
            mRectFIcons.bottom + paddingProgressView,
            mCanvasRectF.right,
            mCanvasRectF.bottom
        )

        mRectProgress.set(
            mRectFBattery.left + paddingProgress,
            mRectFBattery.top + paddingProgress,
            mRectFBattery.left + paddingProgress + (mCanvasRectF.width() - paddingProgress) * progress / 100f,
            mRectFBattery.bottom - paddingProgress
        )

    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {

        val stepIcon = (mCanvasRectF.width() - paddingIconHorizontal) / listIcon.size
        listIcon.forEachIndexed { index, i ->
            val xIcon = mRectFIcons.left + paddingIconHorizontal + stepIcon * index
            val rectIcon =
                RectF(xIcon, mRectFIcons.top, xIcon + stepIcon * 0.3f, mRectFIcons.bottom)
            canvas.drawBitmap(i, null, rectIcon, mPaintIcon)
        }
        mPaintBattery.apply {
            style = Paint.Style.STROKE
            strokeWidth = 4f
            color = strokeColor
        }
        canvas.drawRoundRect(mRectFBattery, 250f, 250f, mPaintBattery)
        val layerId = canvas.saveLayer(mCanvasRectF, null)
        mPaintProgress.color = progressColor
        canvas.drawRoundRect(mRectProgress, 250f, 250f, mPaintProgress)
        mPaintProgress.color = desProgressColor
        mPaintProgress.style = Paint.Style.STROKE
        mPaintProgress.strokeWidth = 10f
        mPaintProgress.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
        val stepLine = mRectFBattery.width() / countOfLine
        (1..countOfLine).forEach {
            val xTop = mRectProgress.left + it * stepLine
            val xBottom = findXForFixedY(xTop, mRectProgress.top, mRectProgress.bottom)
            Path().apply {
                moveTo(xTop, mRectProgress.top)
                lineTo(xBottom, mRectProgress.bottom)
                canvas.drawPath(this, mPaintProgress)
            }
        }
        canvas.restoreToCount(layerId)
        mPaintProgress.xfermode = null
        super.onDraw(canvas)
    }

    fun findXForFixedY(x: Float, y: Float, yBelow: Float, angleDeg: Float = 70f): Float {
        val rad = Math.toRadians((180 - angleDeg).toDouble())
        val tan = tan(rad)
        return (x - (y - yBelow) / tan).toFloat()
    }
}