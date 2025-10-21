package com.example.unsplash.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.panpf.sketch.loadImage
import com.github.panpf.sketch.request.blurHashPlaceholder

fun ImageView.loadUnsplashImage(
    blurHash: String , imageSource: String
) {
    loadImage(imageSource) {
        blurHashPlaceholder(blurHash)
        crossfade(true)
    }
}