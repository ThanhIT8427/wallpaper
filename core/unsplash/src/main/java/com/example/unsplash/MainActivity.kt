package com.example.unsplash

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.base.ui.layoutmanager.GridLayoutManagerPercent
import com.example.unsplash.databinding.ActivityMainBinding
import com.example.unsplash.features.photos.PhotoDiffUtil
import com.example.unsplash.features.photos.UnSplashPhotoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewmodel: MainViewModel by viewModels()
    private val unSplashPhotoAdapter = UnSplashPhotoAdapter(PhotoDiffUtil)
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setupView() {
        val layoutManager =
            GridLayoutManagerPercent(0.4f , this , 2 , RecyclerView.VERTICAL , false)
        binding.apply {
            rcvPhoto.layoutManager = layoutManager
            rcvPhoto.adapter = unSplashPhotoAdapter
        }
    }

    override fun setupObserver() {
        super.setupObserver()
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.photos.collect {
                    unSplashPhotoAdapter.submitData(it)
                }
            }
        }
    }

}