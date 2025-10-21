package com.example.unsplash

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash.api.PhotoRemoteService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRemoteService: PhotoRemoteService,
    private val application: Application
) : AndroidViewModel(application) {

    init {
        viewModelScope.launch {
            try {
                val data = photoRemoteService.getPhotos(
                    mapOf(
                        "page" to 1,
                        "per_page" to 20
                    )
                )
                Log.d("Thanh123", "data: $data")
            } catch (e: Exception) {
                Log.d("Thanh123", "Fail: ${e.message}")
            }
        }
    }

    fun setup() {

    }
}