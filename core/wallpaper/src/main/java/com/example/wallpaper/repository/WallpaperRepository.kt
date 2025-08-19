package com.example.wallpaper.repository

import com.example.wallpaper.R
import com.example.wallpaper.models.WallpaperModel

class WallpaperRepository {
    val listWallpaper = listOf(
        WallpaperModel("2", R.drawable.wallpaper_2, "Wallpaper 2"),
        WallpaperModel("3", R.drawable.wallpaper_3, "Wallpaper 3"),
        WallpaperModel("4", R.drawable.wallpaper_4, "Wallpaper 4"),
        WallpaperModel("5", R.drawable.wallpaper_5, "Wallpaper 5"),
        WallpaperModel("6", R.drawable.wallpaper_6, "Wallpaper 6"),
        WallpaperModel("7", R.drawable.wallpaper_7, "Wallpaper 7"),
        WallpaperModel("8", R.drawable.wallpaper_8, "Wallpaper 8"),
        WallpaperModel("9", R.drawable.wallpaper_9, "Wallpaper 9"),
        WallpaperModel("10", R.drawable.wallpaper_10, "Wallpaper 10"),
        WallpaperModel("11", R.drawable.wallpaper_11, "Wallpaper 11"),
        WallpaperModel("12", R.drawable.wallpaper_12, "Wallpaper 12")
    )

    fun getWallpaperModelById(id: String) = listWallpaper.find { it.wallpaperID == id }
}