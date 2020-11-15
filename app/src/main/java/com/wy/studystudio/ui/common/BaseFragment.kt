package com.wy.studystudio.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.wy.studystudio.BR
import com.wy.studystudio.R
import com.wy.studystudio.ui.strategy.StrategyViewModel
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

    lateinit var content: ViewGroup

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
        content = vdb.root as ViewGroup
        initData()
        initView(content)
        return content
    }

    open fun initView(content: ViewGroup) {

    }

    open fun initData(){}

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}