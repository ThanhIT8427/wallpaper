package com.example.thanhvip123wallpaper

import android.content.Context
import com.example.base.ui.BaseAdapter
import com.example.thanhvip123wallpaper.databinding.ItemOverdrawWallpaperBinding
import com.example.wallpaper.models.WallpaperModel

class OverDrawWallpaperAdapter(val context: Context) :
    BaseAdapter<ItemOverdrawWallpaperBinding, WallpaperModel>() {
    override val layoutId: Int
        get() = R.layout.item_overdraw_wallpaper

    override fun bind(
        data: WallpaperModel,
        binding: ItemOverdrawWallpaperBinding,
        position: Int
    ) {
        binding.apply {
            imgWallpaper.setImageResource(data.wallpaperSource)
        }
    }
}