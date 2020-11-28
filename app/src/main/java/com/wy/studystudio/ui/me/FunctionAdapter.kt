package com.wy.studystudio.ui.me

import android.view.View

import com.wy.studystudio.R
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.adapter.BaseAdapter

/**
 *
 * @author  cantalou
 * @date    2020年11月14日 17:39
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class FunctionAdapter : BaseAdapter<Function>() {

    fun openFunction(v: View, function: Function) {
        v.context.startFragment(function.className)
    }

    override fun layoutId(): Array<Int> {
        return arrayOf(R.layout.item_function)
    }
}