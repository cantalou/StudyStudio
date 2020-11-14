package com.wy.studystudio.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.wy.studystudio.BR
import com.wy.studystudio.R
import com.wy.studystudio.ui.common.DBViewHolder

/**
 *
 * @author  cantalou
 * @date    2020年11月14日 17:39
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class FunctionAdapter(val data: MutableList<Function>) : RecyclerView.Adapter<DBViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBViewHolder {
        val vdb = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.item_function, parent, false)
        return DBViewHolder(vdb)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        holder.vdb.setVariable(BR.function, data[position])
        holder.vdb.setVariable(BR.adapter, this)
    }

    fun openFunction(v: View, function: Function) {
        v.context.startActivity(Intent(function.action))
    }
}