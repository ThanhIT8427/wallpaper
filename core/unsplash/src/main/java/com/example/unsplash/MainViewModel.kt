package com.example.unsplash

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.unsplash.api.PhotoRemoteService
import com.example.unsplash.api.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val application: Application
) : AndroidViewModel(application) {

    val photos = photoRepository.pager
}