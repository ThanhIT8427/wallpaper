package com.example.thanhvip123wallpaper

import android.content.Context
import androidx.core.net.toUri
import com.example.base.ui.BaseAdapter
import com.example.thanhvip123wallpaper.databinding.ItemWallpaperBinding
import com.example.wallpaper.models.WallpaperModel

class WallpaperAdapter(val context: Context) : BaseAdapter<ItemWallpaperBinding, WallpaperModel>() {

    override val layoutId: Int
        get() = R.layout.item_wallpaper

    override fun bind(data: WallpaperModel, binding: ItemWallpaperBinding, position: Int) {
        binding.imgWallpaper.setImageResource(data.wallpaperSource)
        binding.root.setOnClickListener {
            baseAdapterInterface?.onClick(data, binding.cardWallpaper)
        }
    }
}