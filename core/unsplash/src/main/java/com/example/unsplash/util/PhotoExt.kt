package com.example.unsplash.util

import com.example.unsplash.api.PhotoResponse
import com.example.unsplash.local.PhotoEntity

fun PhotoResponse.toPhotoEntity(): PhotoEntity = PhotoEntity(id, blur_hash, urls.raw)