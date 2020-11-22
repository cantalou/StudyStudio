package com.wy.studystudio.ui.task.fragment

import android.net.Uri
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentContentImageBinding
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.task.model.Content

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月21日 23:20
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ContentImageFragment : BaseFragment<FragmentContentImageBinding>() {

    lateinit var content: Content

    override fun layoutId(): Int {
        return R.layout.fragment_content_image
    }

    override fun initData() {
        content = requireArguments().getParcelable<Content>("content")!!
    }

    override fun initView(viewRoot: ViewGroup) {
        super.initView(viewRoot)
        Glide.with(this).load(Uri.parse(content.content)).into(vdb.content)
    }

}