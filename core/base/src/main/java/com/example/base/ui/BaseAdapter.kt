package com.example.base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

interface BaseAdapterInterface<V> {
    fun onClick(data: V)
}

abstract class BaseAdapter<T : ViewDataBinding, V> :
    RecyclerView.Adapter<BaseAdapter<T, V>.ViewHolder>() {
    inner class ViewHolder(val binding: T) : RecyclerView.ViewHolder(binding.root)

    abstract val layoutId: Int
    open var datas: List<V> = listOf()
    abstract fun bind(data: V, binding: T, position: Int)
    open val isSupportOnclickRoot = true
    private var baseAdapterInterface: BaseAdapterInterface<V>? = null

    fun setBaseAdapterInterface(baseAdapterInterface: BaseAdapterInterface<V>) {
        this.baseAdapterInterface = baseAdapterInterface
    }

    fun setData(datas: List<V>) {
        this.datas = datas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DataBindingUtil.inflate<T>(LayoutInflater.from(parent.context), layoutId, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: V = datas[position]
        val binding: T = holder.binding
        bind(data, binding, position)
        if (isSupportOnclickRoot) {
            binding.root.setOnClickListener {
                baseAdapterInterface?.onClick(data)
            }
        }
    }
}