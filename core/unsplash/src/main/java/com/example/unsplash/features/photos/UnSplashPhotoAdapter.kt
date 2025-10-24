package com.example.unsplash.features.photos

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.unsplash.R
import com.example.unsplash.api.PhotoResponse
import com.example.unsplash.databinding.ItemWidgetPhotoBinding
import com.example.unsplash.local.PhotoEntity
import com.example.unsplash.util.loadUnsplashImage


class UnSplashPhotoAdapter(diffUtil: DiffUtil.ItemCallback<PhotoEntity>) :
    BaseAdapter<ItemWidgetPhotoBinding , PhotoEntity>(diffUtil) {
    override val layoutId: Int
        get() = R.layout.item_widget_photo

    override fun bind(
        data: PhotoEntity ,
        binding: ItemWidgetPhotoBinding ,
        position: Int
    ) {
        Log.d("Thanh123","Data: $data")
        binding.imgWidgetPrevious.loadUnsplashImage(
            data.blurhash , data.source
        )
    }
}

object PhotoDiffUtil : DiffUtil.ItemCallback<PhotoEntity>() {
    override fun areItemsTheSame(
        oldItem: PhotoEntity ,
        newItem: PhotoEntity
    ): Boolean {
        return false
    }

    override fun areContentsTheSame(
        oldItem: PhotoEntity ,
        newItem: PhotoEntity
    ): Boolean {
        return false
    }
}