package com.wy.studystudio.ui.task.fragment

import android.os.Bundle
import android.view.View
import com.wy.studystudio.R
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.adapter.BaseAdapter
import com.wy.studystudio.ui.strategy.model.Strategy

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 22:50
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ContentAdapter : BaseAdapter<Strategy>() {

    override fun layoutId(): Int {
        return R.layout.item_strategy
    }

    fun edit(view: View, strategy: Strategy) {
        view.context.startFragment(EditContentFragment::class.java, 0, Bundle().apply { putLong("taskId", strategy.id) })
    }
}