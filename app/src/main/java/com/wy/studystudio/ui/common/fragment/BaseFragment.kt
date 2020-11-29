package com.wy.studystudio.ui.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.wy.studystudio.BR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 10:44
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment(), CoroutineScope by MainScope() {

    lateinit var viewRoot: ViewGroup

    lateinit var vdb: T

    open fun isToolBarOverlay(): Boolean {
        return false
    }

    open fun title(): String {
        return ""
    }

    abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vdb = DataBindingUtil.inflate<T>(inflater, layoutId(), container, false)
        vdb.setVariable(BR.fragment, this)
        viewRoot = vdb.root as ViewGroup
        initData()
        initView(viewRoot, inflater)
        return viewRoot
    }

    open fun initView(viewRoot: ViewGroup, inflater: LayoutInflater) {

    }

    open fun initData() {}

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}