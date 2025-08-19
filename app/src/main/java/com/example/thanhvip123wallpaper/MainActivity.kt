package com.example.thanhvip123wallpaper

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.ui.BaseActivity
import com.example.base.ui.BaseAdapterInterface
import com.example.thanhvip123wallpaper.databinding.ActivityMainBinding
import com.example.thanhvip123wallpaper.databinding.DialogChooseWallpaperBinding
import com.example.wallpaper.controller.WallpaperController
import com.example.wallpaper.models.WallpaperModel
import com.example.wallpaper.repository.WallpaperRepository

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val adapter = WallpaperAdapter()
    override val layoutId: Int
        get() = R.layout.activity_main

    override val idMain: Int
        get() = R.id.main

    override fun setupView() {
        setupViewWallpaper()
    }

    fun setupViewWallpaper() {
        binding.txtHeader.text = "Wallpaper"
        binding.rcvWallpaper.adapter = adapter
        binding.rcvWallpaper.layoutManager =
            GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        adapter.setData(WallpaperRepository().listWallpaper)
        adapter.setBaseAdapterInterface(object : BaseAdapterInterface<WallpaperModel> {
            override fun onClick(data: WallpaperModel) {
                showPopupDeleteAllHistory(data)
            }
        })
    }

    private fun showPopupDeleteAllHistory(data: WallpaperModel) {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: DialogChooseWallpaperBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_choose_wallpaper,
            null, false
        )
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = false
        val popupWindow = PopupWindow(popupView.root, width, height, focusable)
        popupWindow.isOutsideTouchable = false
        popupWindow.elevation = 10f
        popupView.apply {
            imgWallpaper.setImageResource(data.wallpaperSource)
            btnSetHome.setOnClickListener {
                WallpaperController(root.context).setWallpaperByBitmap(
                    BitmapFactory.decodeResource(
                        root.resources,
                        data.wallpaperSource
                    ), listOf(WallpaperManager.FLAG_SYSTEM)
                )
            }

            btnSetLockScreen.setOnClickListener {
                WallpaperController(root.context).setWallpaperByBitmap(
                    BitmapFactory.decodeResource(
                        root.resources,
                        data.wallpaperSource
                    ), listOf(WallpaperManager.FLAG_LOCK)
                )
            }

            btnSetBoth.setOnClickListener {
                WallpaperController(root.context).setWallpaperByBitmap(
                    BitmapFactory.decodeResource(
                        root.resources,
                        data.wallpaperSource
                    ), listOf(WallpaperManager.FLAG_LOCK, WallpaperManager.FLAG_SYSTEM)
                )
            }
        }
        popupWindow.showAtLocation(popupView.root, Gravity.CENTER, 0, 0)
    }
}