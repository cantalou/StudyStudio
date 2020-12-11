package com.wy.studystudio.ui.task.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.wy.studystudio.R
import com.wy.studystudio.databinding.ItemContentImageBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.adapter.BaseAdapter
import com.wy.studystudio.ui.common.adapter.DBViewHolder
import com.wy.studystudio.ui.task.model.Content
import com.wy.studystudio.ui.task.vm.TaskViewModel

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 22:50
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ContentAdapter : BaseAdapter<Content>() {

    var corner: Int = 0

    override fun layoutId(): Array<Int> {
        return arrayOf(R.layout.item_content_text, R.layout.item_content_image, R.layout.item_content_image)
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    fun edit(view: View, content: Content) {
        view.context.startFragment(EditContentFragment::class.java, 0, bundleOf("content" to content))
    }

    fun delete(position: Int) {
        val item = data.removeAt(position)
        notifyDataSetChanged()
        val taskVM = gvm(TaskViewModel::class.java)
        val task = taskVM.get(item.taskId)
        task.contents.removeAt(position)
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (corner == 0) {
            corner = context.resources.getDimensionPixelOffset(R.dimen.corner)
        }
        when (data[position].type) {
            Content.TYPE_TEXT -> {
            }
            Content.TYPE_IMAGE -> {
                Glide.with(holder.itemView).load(data[position].content)
                    .into((holder.vdb as ItemContentImageBinding).image)
            }
        }
    }
}