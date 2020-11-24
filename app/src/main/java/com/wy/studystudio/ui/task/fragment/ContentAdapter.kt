package com.wy.studystudio.ui.task.fragment

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.wy.studystudio.R
import com.wy.studystudio.databinding.ItemContentImageBinding
import com.wy.studystudio.databinding.ItemContentTextBinding
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.adapter.BaseAdapter
import com.wy.studystudio.ui.common.adapter.DBViewHolder
import com.wy.studystudio.ui.task.model.Content

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 22:50
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ContentAdapter : BaseAdapter<Content>() {

    override fun layoutId(): Array<Int> {
        return arrayOf(R.layout.item_content_text, R.layout.item_content_image, R.layout.item_content_image)
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    fun edit(view: View, content: Content) {
        view.context.startFragment(EditContentFragment::class.java, 0, Bundle().apply { putParcelable("content", content) })
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        when(data[position].type){
            Content.TYPE_TEXT -> {
            }
            Content.TYPE_IMAGE -> {
                Glide.with(holder.itemView).load(data[position].content).into((holder.vdb as ItemContentImageBinding).image)
            }
        }
    }
}