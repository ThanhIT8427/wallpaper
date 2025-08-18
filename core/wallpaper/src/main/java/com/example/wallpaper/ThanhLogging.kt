package com.example.wallpaper

import android.util.Log
val Any.name: String
    get() = this::class.simpleName ?: "Unknown"
fun Any.ThanhLogging(message: String) = Log.d("Thanh123_$name",message)