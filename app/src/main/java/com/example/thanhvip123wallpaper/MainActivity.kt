package com.example.thanhvip123wallpaper

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.viewpager2.widget.CompositePageTransformer
import com.example.base.ui.BaseActivity
import com.example.base.ui.BaseAdapterInterface
import com.example.base.ui.helper.ScalePagerTransformer
import com.example.base.ui.helper.SliderPageTransformer
import com.example.thanhvip123wallpaper.databinding.ActivityMainBinding
import com.example.wallpaper.models.WallpaperModel
import com.example.wallpaper.repository.WallpaperRepository

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var adapter : WallpaperAdapter
    override val layoutId: Int
        get() = R.layout.activity_main

    override val idMain: Int
        get() = R.id.main

    override fun setupView() {
        adapter = WallpaperAdapter(this)
        setupViewWallpaper()
    }

    fun setupViewWallpaper() {
        binding.txtHeader.text = "Wallpaper"
        binding.apply {
            with(vpWallpaper) {
                clipChildren = false
                clipToPadding = false
                offscreenPageLimit = 3
            }
            val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset_between_page)
            val pageMargin = resources.getDimensionPixelOffset(R.dimen.margin_between_page)
            val pageTransformer = SliderPageTransformer(pageMargin, pageOffset)
            val scalePagerTransformer = ScalePagerTransformer(0.85f)
            val pageTransformerComposite = CompositePageTransformer().also {
                it.addTransformer(scalePagerTransformer)
                it.addTransformer(pageTransformer)
            }
            vpWallpaper.setPageTransformer(pageTransformerComposite)
        }
        binding.vpWallpaper.adapter = adapter
        adapter.setData(WallpaperRepository().listWallpaper)
        adapter.setBaseAdapterInterface(object : BaseAdapterInterface {
            @SuppressLint("UnsafeIntentLaunch")
            override fun onClick(data: Any, view: View) {
                val realData = data as WallpaperModel
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    putExtra("id", realData.wallpaperID)
                }
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MainActivity,
                    view,
                    view.transitionName
                )
                startActivity(intent, options.toBundle())
            }

        })
    }

}