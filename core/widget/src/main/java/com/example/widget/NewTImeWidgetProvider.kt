package com.example.widget

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class NewTImeWidgetProvider: BroadcastReceiver() {


    val ACTION_APPWIDGET_ENABLE_AND_UPDATE: String = ("android.appwidget.action"
            + ".APPWIDGET_ENABLE_AND_UPDATE")
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent!!.action
        val data = intent.extras?.getString("Thanh123") ?: "CCC"
        Log.d("Thanh123","action: $action - data: $data")
        if (ACTION_APPWIDGET_ENABLE_AND_UPDATE.equals(action)) {
            this.onReceive(
                context, Intent(intent)
                    .setAction(AppWidgetManager.ACTION_APPWIDGET_ENABLED)
            )
            this.onReceive(
                context, Intent(intent)
                    .setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
            )
        } else if (AppWidgetManager.ACTION_APPWIDGET_UPDATE == action) {
            val extras = intent.getExtras()
            if (extras != null) {
                val appWidgetIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS)
                if (appWidgetIds != null && appWidgetIds.size > 0) {
//                    this.onUpdate(context, AppWidgetManager.getInstance(context), appWidgetIds)
                }
            }
        } else if (AppWidgetManager.ACTION_APPWIDGET_DELETED == action) {
            val extras = intent.getExtras()
            if (extras != null && extras.containsKey(AppWidgetManager.EXTRA_APPWIDGET_ID)) {
                val appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID)
//                this.onDeleted(context, intArrayOf(appWidgetId))
            }
        } else if (AppWidgetManager.ACTION_APPWIDGET_OPTIONS_CHANGED == action) {
            val extras = intent.getExtras()
            if (extras != null && extras.containsKey(AppWidgetManager.EXTRA_APPWIDGET_ID)
                && extras.containsKey(AppWidgetManager.EXTRA_APPWIDGET_OPTIONS)
            ) {
                val appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID)
                val widgetExtras = extras.getBundle(AppWidgetManager.EXTRA_APPWIDGET_OPTIONS)
//                this.onAppWidgetOptionsChanged(
//                    context, AppWidgetManager.getInstance(context),
//                    appWidgetId, widgetExtras
//                )
            }
        } else if (AppWidgetManager.ACTION_APPWIDGET_ENABLED == action) {
//            this.onEnabled(context)
        } else if (AppWidgetManager.ACTION_APPWIDGET_DISABLED == action) {
//            this.onDisabled(context)
        } else if (AppWidgetManager.ACTION_APPWIDGET_RESTORED == action) {
            val extras = intent.getExtras()
            if (extras != null) {
                val oldIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_OLD_IDS)
                val newIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS)
                if (oldIds != null && oldIds.size > 0) {
//                    this.onRestored(context, oldIds, newIds)
//                    this.onUpdate(context, AppWidgetManager.getInstance(context), newIds)
                }
            }
        }
    }
}