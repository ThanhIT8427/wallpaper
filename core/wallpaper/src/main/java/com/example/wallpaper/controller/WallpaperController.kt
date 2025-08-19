package com.example.wallpaper.controller

import android.Manifest
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.annotation.RequiresPermission
import com.example.wallpaper.ThanhLogging

class WallpaperController(context: Context) {
    private val wallpaperManager = WallpaperManager.getInstance(context)
    private val isWallpaperSupport = wallpaperManager.isWallpaperSupported

    @RequiresPermission(Manifest.permission.SET_WALLPAPER)
    fun setWallpaperByBitmap(bitmap: Bitmap, listTypeSet: List<Int>) {
        if(isWallpaperSupport) {
            listTypeSet.forEach {
                wallpaperManager.setBitmap(
                    bitmap, null,
                    true, it
                )
            }
        } else {
            ThanhLogging("Device Unsupport Wallpaper")
        }
    }
}