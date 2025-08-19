package com.example.thanhvip123wallpaper

import androidx.lifecycle.ViewModel
import com.example.wallpaper.models.WallpaperModel
import com.example.wallpaper.repository.WallpaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WallPaperViewModel @Inject constructor(): ViewModel() {
    private val wallpaperRepository = WallpaperRepository()
    private var _currentWallpaper: MutableStateFlow<WallpaperModel?> = MutableStateFlow(null)
    val currentWallpaper = _currentWallpaper.asStateFlow()

    fun setWallpaperById(id: String): Boolean {
        val wallpaper = wallpaperRepository.getWallpaperModelById(id)
        wallpaper?.let {
            _currentWallpaper.update {
                wallpaper
            }
            return true
        }
        return false
    }
}