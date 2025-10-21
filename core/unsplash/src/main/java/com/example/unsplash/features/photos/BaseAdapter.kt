package com.example.unsplash.features.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

interface BaseAdapterInterface {
    fun onClick(data: Any , view: View)
}

abstract class BaseAdapter<T : ViewDataBinding , V : Any>(diffUtil: DiffUtil.ItemCallback<V>) :
    PagingDataAdapter<V , BaseAdapter<T , V>.ViewHolder>(diffUtil) {
    inner class ViewHolder(val binding: T) : RecyclerView.ViewHolder(binding.root)

    abstract val layoutId: Int
    abstract fun bind(data: V , binding: T , position: Int)
    protected var baseAdapterInterface: BaseAdapterInterface? = null

    fun setMBaseAdapterInterface(baseAdapterInterface: BaseAdapterInterface) {
        this.baseAdapterInterface = baseAdapterInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        val binding =
            DataBindingUtil.inflate<T>(
                LayoutInflater.from(parent.context) ,
                layoutId ,
                parent ,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        val data: V = getItem(position) ?: return
        val binding: T = holder.binding
        bind(data , binding , position)
    }
}