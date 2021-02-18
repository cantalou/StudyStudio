package com.wy.studystudio.ui.task.fragment

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wy.studystudio.ui.task.model.Content
import com.wy.studystudio.ui.task.model.Task

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月21日 23:16
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ShowContentAdapter(fragment: Fragment, val task: Task) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return task.contents.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (task.contents[position].type) {
            Content.TYPE_VIDEO -> ContentVideoFragment(this)
            Content.TYPE_IMAGE -> ContentImageFragment(this)
            Content.TYPE_LINK -> ContentLinkFragment(this)
            else -> ContentTextFragment()
        }.apply {
            arguments = bundleOf("content" to task.contents[position], "position" to position, "total" to itemCount)
        }
    }
}