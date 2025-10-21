package com.example.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.util.SizeF
import android.util.TypedValue
import android.view.View
import android.widget.RemoteViews

class AddWidgetBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val data = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        )
        val dataSend = intent?.getIntExtra("Thanh123", -1)
        Log.d("Thanh123", "AddWidgetBroadCast - data: $data - dataSend: $dataSend")
        if (dataSend != -1) {
            val widgetManager = AppWidgetManager.getInstance(context)
            when (dataSend) {
                1 -> {
                    val intent = PendingIntent.getActivity(
                        context, 0, Intent(
                            context,
                            WidgetConfigurationActivity::class.java
                        ), PendingIntent.FLAG_IMMUTABLE
                    )
                    if (context != null && data != null) {
                        val options = widgetManager?.getAppWidgetOptions(data)
                        val sizes = options?.getParcelableArrayList<SizeF>(
                            AppWidgetManager.OPTION_APPWIDGET_SIZES
                        )
                        Log.d("Thanh123", "AddWidgetBroadCast Size - $sizes")
                        val customView = TestLayout(context)
                        customView.measure(
                            View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.EXACTLY),
                            View.MeasureSpec.makeMeasureSpec(200, View.MeasureSpec.EXACTLY)
                        )
                        customView.layout(0, 0, customView.measuredWidth, customView.measuredHeight)

                        val bitmap = getBitmapFromView(customView)
                        val remoteView =
                            RemoteViews(context.packageName, R.layout.widget_time).apply {
                                setOnClickPendingIntent(R.id.btnNextData, intent)
                                setTextViewText(R.id.btnNextData, "ID: $dataSend")
                                setImageViewBitmap(R.id.imgCustomView, bitmap)
                                if (sizes?.isNotEmpty() == true) {
                                    Log.d("Thanh123","setHeight")
                                    setViewLayoutHeight(
                                        R.id.root,
                                        sizes[0].width,
                                        TypedValue.COMPLEX_UNIT_DIP
                                    )
                                }
                            }
                        data.let {
                            Log.d("Thanh123", "Data: $data")
                            widgetManager.updateAppWidget(data, remoteView)
                        }
                    }
                }

                0 -> {
                    val intent = PendingIntent.getActivity(
                        context, 0, Intent(
                            context,
                            TestWidgetView::class.java
                        ), PendingIntent.FLAG_IMMUTABLE
                    )
                    val remoteView = RemoteViews(context?.packageName, R.layout.widget_time).apply {
                        setOnClickPendingIntent(R.id.btnNextData, intent)
                        setTextViewText(R.id.btnNextData, "ID: $dataSend")
                    }
                    data?.let {
                        Log.d("Thanh123", "Data: $data")
                        widgetManager.updateAppWidget(data, remoteView)
                    }
                }
            }
        }
    }

    fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}