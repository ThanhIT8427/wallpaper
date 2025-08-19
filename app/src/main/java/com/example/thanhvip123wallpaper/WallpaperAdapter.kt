package com.example.thanhvip123wallpaper

import com.example.base.ui.BaseAdapter
import com.example.thanhvip123wallpaper.databinding.ItemWallpaperBinding
import com.example.wallpaper.models.WallpaperModel

class WallpaperAdapter : BaseAdapter<ItemWallpaperBinding, WallpaperModel>() {

    override val layoutId: Int
        get() = R.layout.item_wallpaper

    override fun bind(data: WallpaperModel, binding: ItemWallpaperBinding, position: Int) {
        binding.imgWallpaper.setImageResource(data.wallpaperSource)
    }
}