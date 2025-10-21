package com.example.wallpaper.repository

import com.example.wallpaper.R
import com.example.wallpaper.models.WallpaperModel

class WallpaperRepository {
    val listWallpaper = listOf(
        WallpaperModel("2", R.drawable.sword_samurai, "Wallpaper 2"),
        WallpaperModel("3", R.drawable.sword_samurai, "Wallpaper 3"),
        WallpaperModel("4", R.drawable.sword_samurai, "Wallpaper 4"),
        WallpaperModel("5", R.drawable.sword_samurai, "Wallpaper 5"),
        WallpaperModel("6", R.drawable.sword_samurai, "Wallpaper 6"),
        WallpaperModel("7", R.drawable.sword_samurai, "Wallpaper 7"),
        WallpaperModel("8", R.drawable.sword_samurai, "Wallpaper 8"),
        WallpaperModel("9", R.drawable.sword_samurai, "Wallpaper 9"),
        WallpaperModel("10", R.drawable.sword_samurai, "Wallpaper 10"),
        WallpaperModel("11", R.drawable.sword_samurai, "Wallpaper 11"),
        WallpaperModel("12", R.drawable.sword_samurai, "Wallpaper 12")
    )

    fun getWallpaperModelById(id: String) = listWallpaper.find { it.wallpaperID == id }
}