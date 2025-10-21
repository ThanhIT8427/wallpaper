package com.example.thanhvip123wallpaper

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.ui.BaseActivity
import com.example.base.ui.BaseAdapterInterface
import com.example.base.ui.itemdecoration.OverDrawItemDecoration
import com.example.base.ui.layoutmanager.LinearLayoutManagerPercent
import com.example.thanhvip123wallpaper.databinding.ActivityOverDrawWallpaperBinding
import com.example.wallpaper.models.WallpaperModel
import com.example.wallpaper.repository.WallpaperRepository

class OverDrawWallpaperActivity : BaseActivity<ActivityOverDrawWallpaperBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_over_draw_wallpaper

    override val idMain: Int?
        get() = R.id.main

    private val adapter = OverDrawWallpaperAdapter(this)

    override fun setupView() {
        setupViewWallpaper()
    }

    fun setupViewWallpaper() {
        binding.txtHeader.text = "OverDrawWallpaper"
        binding.rcvWallpaper.adapter = adapter
        binding.rcvWallpaper.addItemDecoration(
            OverDrawItemDecoration(0.8f)
        )
        binding.rcvWallpaper.layoutManager = LinearLayoutManagerPercent(
            0.35f, this, LinearLayoutManager.HORIZONTAL, false
        )
        adapter.setData(WallpaperRepository().listWallpaper)
        adapter.setBaseAdapterInterface(object : BaseAdapterInterface<WallpaperModel> {
            override fun onClick(data: WallpaperModel) {
                WallpaperSelectActivity.start(data.wallpaperID, this@OverDrawWallpaperActivity)
            }
        })
    }

}