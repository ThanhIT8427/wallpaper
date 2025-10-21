package com.example.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TestWidgetView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test_widget_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val data = intent.getIntExtra("Thanh123", 8)
        Log.d("Thanh123", "data: $data")
        findViewById<Button>(R.id.btnAddTimeWidget).setOnClickListener {
            createWidget(1)
        }
        findViewById<Button>(R.id.btnAddTimeWidgetNoData).setOnClickListener {
            createWidget(0)
        }

    }

    fun createWidget(widgetId: Int) {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val myProvider = ComponentName(this, TimeWidgetProvider::class.java)
        val successCallback = PendingIntent.getBroadcast(
            this,
            0,
            Intent(this, AddWidgetBroadCast::class.java).apply {
                putExtra("Thanh123",widgetId)
            },
            PendingIntent.FLAG_MUTABLE
        )
        if (appWidgetManager.isRequestPinAppWidgetSupported) {
            appWidgetManager.requestPinAppWidget(myProvider, null, successCallback)
        }
    }
}