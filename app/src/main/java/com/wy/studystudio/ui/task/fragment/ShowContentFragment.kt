package com.wy.studystudio.ui.task.fragment

import android.net.Uri
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.wy.studystudio.BR
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
open class ShowContentFragment<T : ViewDataBinding> : BaseFragment<T>() {

    lateinit var content: Content

    var position: Int = 0

    var total: Int = 0

    override fun layoutId(): Int {
        return R.layout.fragment_content_image
    }

    override fun initData() {
        requireArguments().apply {
            content = getParcelable<Content>("content")!!
            position = getInt("position")
            total = getInt("total")
        }
    }

    override fun initView(viewRoot: ViewGroup) {
        super.initView(viewRoot)
        vdb.setVariable(BR.content, content)
        vdb.setVariable(BR.position, position)
        vdb.setVariable(BR.total, total)
    }

}