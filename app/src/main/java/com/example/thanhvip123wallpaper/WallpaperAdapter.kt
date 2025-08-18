package com.example.thanhvip123wallpaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thanhvip123wallpaper.databinding.ItemWallpaperBinding
import com.example.wallpaper.models.WallpaperModel

interface WallpaperAdapterInterface {
    fun onClick(data: WallpaperModel)
}
class WallpaperAdapter: RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

    private var listData: List<WallpaperModel> = listOf()

    private var wallpaperAdapterInterface: WallpaperAdapterInterface? = null

    fun setWallpaperAdapterInterface(wallpaperAdapterInterface: WallpaperAdapterInterface) {
        this.wallpaperAdapterInterface = wallpaperAdapterInterface
    }

    fun setData(datas: List<WallpaperModel>) {
        listData = datas
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemWallpaperBinding) : RecyclerView.ViewHolder(binding.root) {
        fun init(data: WallpaperModel) {
            binding.imgWallpaper.setImageResource(data.wallpaperSource)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemWallpaperBinding>(LayoutInflater.from(parent.context), R.layout.item_wallpaper, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.init(listData.get(position))
        holder.binding.root.setOnClickListener {
            wallpaperAdapterInterface?.onClick(listData.get(position))
        }
    }
}