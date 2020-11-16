package com.wy.studystudio.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.wy.studystudio.BR
import com.wy.studystudio.R

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

    abstract fun layoutId(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBViewHolder {
        val vdb = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layoutId(), parent, false)
        return DBViewHolder(vdb)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        holder.vdb.setVariable(BR.item, data[position])
        holder.vdb.setVariable(BR.adapter, this)
        holder.vdb.setVariable(BR.position, position)
    }

    fun notifyDataSetChanged(newData: List<T>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}