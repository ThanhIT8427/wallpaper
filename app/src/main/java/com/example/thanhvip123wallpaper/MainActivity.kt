package com.example.thanhvip123wallpaper

import androidx.recyclerview.widget.GridLayoutManager
import com.example.base.ui.BaseActivity
import com.example.base.ui.BaseAdapterInterface
import com.example.base.ui.GridLayoutManagerPercent
import com.example.base.ui.GridSpacingItemDecoration
import com.example.base.ui.utils.Utils
import com.example.thanhvip123wallpaper.databinding.ActivityMainBinding
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
            GridLayoutManagerPercent(0.6f, this, 2, GridLayoutManager.VERTICAL, false)
        binding.rcvWallpaper.addItemDecoration(
            GridSpacingItemDecoration(2, Utils.floatToDp(this, 16f), false)
        )
        adapter.setData(WallpaperRepository().listWallpaper)
        adapter.setBaseAdapterInterface(object : BaseAdapterInterface<WallpaperModel> {
            override fun onClick(data: WallpaperModel) {
                WallpaperSelectActivity.start(data.wallpaperID, this@MainActivity)
            }
        })
    }

//    private fun showPopupDeleteAllHistory(data: WallpaperModel) {
//        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val popupView: DialogChooseWallpaperBinding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.dialog_choose_wallpaper,
//            null, false
//        )
//        popupView.apply {
//            imgWallpaper.setImageResource(data.wallpaperSource)
//            btnSetHome.setOnClickListener {
//                WallpaperController(root.context).setWallpaperByBitmap(
//                    BitmapFactory.decodeResource(
//                        root.resources,
//                        data.wallpaperSource
//                    ), listOf(WallpaperManager.FLAG_SYSTEM)
//                )
//            }
//
//            btnSetLockScreen.setOnClickListener {
//                WallpaperController(root.context).setWallpaperByBitmap(
//                    BitmapFactory.decodeResource(
//                        root.resources,
//                        data.wallpaperSource
//                    ), listOf(WallpaperManager.FLAG_LOCK)
//                )
//            }
//
//            btnSetBoth.setOnClickListener {
//                WallpaperController(root.context).setWallpaperByBitmap(
//                    BitmapFactory.decodeResource(
//                        root.resources,
//                        data.wallpaperSource
//                    ), listOf(WallpaperManager.FLAG_LOCK, WallpaperManager.FLAG_SYSTEM)
//                )
//            }
//        }
//        Utils.showPopupCenter(popupView.root)
//    }
}