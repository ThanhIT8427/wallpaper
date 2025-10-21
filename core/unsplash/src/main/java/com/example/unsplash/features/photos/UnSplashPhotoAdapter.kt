package com.example.unsplash.features.photos

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.unsplash.R
import com.example.unsplash.api.PhotoResponse
import com.example.unsplash.databinding.ItemWidgetPhotoBinding
import com.example.unsplash.util.loadUnsplashImage


class UnSplashPhotoAdapter(diffUtil: DiffUtil.ItemCallback<PhotoResponse>) :
    BaseAdapter<ItemWidgetPhotoBinding , PhotoResponse>(diffUtil) {
    override val layoutId: Int
        get() = R.layout.item_widget_photo

    override fun bind(
        data: PhotoResponse ,
        binding: ItemWidgetPhotoBinding ,
        position: Int
    ) {
        Log.d("Thanh123","Data: $data")
        binding.imgWidgetPrevious.loadUnsplashImage(
            data.blur_hash , data.urls.regular
        )
    }
}

object PhotoDiffUtil : DiffUtil.ItemCallback<PhotoResponse>() {
    override fun areItemsTheSame(
        oldItem: PhotoResponse ,
        newItem: PhotoResponse
    ): Boolean {
        return false
    }

    override fun areContentsTheSame(
        oldItem: PhotoResponse ,
        newItem: PhotoResponse
    ): Boolean {
        return false
    }
}