package com.wy.studystudio.ui.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.wy.studystudio.BR

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 22:51
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
abstract class BaseAdapter<T>() : RecyclerView.Adapter<DBViewHolder>() {

    var data: MutableList<T> = mutableListOf()

    lateinit var context: Context

    abstract fun layoutId(): Array<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBViewHolder {
        return DBViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layoutId()[viewType], parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        holder.vdb.setVariable(BR.item, data[position])
        holder.vdb.setVariable(BR.adapter, this)
        holder.vdb.setVariable(BR.position, position)
    }

    open fun notifyDataSetChanged(newData: List<T>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
}