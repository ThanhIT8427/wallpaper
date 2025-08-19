package com.example.thanhvip123wallpaper

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.base.ui.BaseActivity
import com.example.thanhvip123wallpaper.databinding.ActivityWallpaperSelectBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WallpaperSelectActivity : BaseActivity<ActivityWallpaperSelectBinding>() {

    val viewmodel: WallPaperViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.activity_wallpaper_select

    override val idMain: Int
        get() = R.id.main


    override fun setupView() {
        val wallpaperId = intent.getStringExtra(wallpaperIdKey)
        wallpaperId?.let {
            viewmodel.setWallpaperById(it)
        }
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
            btnWatchAds.setOnClickListener {
                ChoiceWallpaperScreenFragment().show(
                    supportFragmentManager, "ChoiceWallpaperScreenFragment"
                )
            }
        }
    }

    override fun setupObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.currentWallpaper.collectLatest {
                    if (it != null) {
                        binding.imgWallpaper.setImageResource(it.wallpaperSource)
                    }
                }
            }
        }
    }

    companion object {
        const val wallpaperIdKey = "WALLPAPER_ID_KEY"
        fun start(wallpaperID: String, context: Context) {
            Intent(context, WallpaperSelectActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(wallpaperIdKey, wallpaperID)
                context.startActivity(this)
            }
        }
    }
}