package com.wy.studystudio.ui.me.strategy.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.wy.studystudio.R
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.adapter.BaseAdapter
import com.wy.studystudio.ui.me.strategy.model.Strategy

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 22:50
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class StrategyAdapter : BaseAdapter<Strategy>() {

    override fun layoutId(): Array<Int> {
        return arrayOf(R.layout.item_strategy)
    }

    fun edit(view: View, strategy: Strategy) {
        view.context.startFragment(EditStrategyFragment::class.java, 0, bundleOf("strategyId" to strategy.id))
    }
}