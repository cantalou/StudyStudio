package com.wy.studystudio.ui.me.config.fragment

import com.wy.studystudio.BR
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentConfigBinding
import com.wy.studystudio.extension.sp
import com.wy.studystudio.ui.common.fragment.BaseFragment

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月28日 17:37
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ConfigFragment : BaseFragment<FragmentConfigBinding>() {

    override fun layoutId(): Int {
        return R.layout.fragment_config
    }

    override fun title(): String {
        return "配置"
    }

    override fun initData() {
        super.initData()
        vdb.setVariable(BR.config, sp)
    }
}