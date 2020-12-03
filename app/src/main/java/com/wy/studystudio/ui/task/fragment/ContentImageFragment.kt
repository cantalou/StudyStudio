package com.wy.studystudio.ui.task.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.davemorrissey.labs.subscaleview.ImageSource
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentContentImageBinding

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月21日 23:20
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ContentImageFragment(adapter: ShowContentAdapter) : ShowContentFragment<FragmentContentImageBinding>(adapter) {

    override fun contentLayoutId(): Int {
        return R.layout.fragment_content_image
    }

    override fun initView(viewRoot: ViewGroup, inflater: LayoutInflater) {
        super.initView(viewRoot, inflater)
        cvdb.content.setImage(ImageSource.uri(content.content))
    }

}