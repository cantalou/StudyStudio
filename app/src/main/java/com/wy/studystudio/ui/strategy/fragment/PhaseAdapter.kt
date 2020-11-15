package com.wy.studystudio.ui.strategy.fragment

import com.wy.studystudio.R
import com.wy.studystudio.ui.common.BaseAdapter
import com.wy.studystudio.ui.strategy.model.Phase

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 23:12
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class PhaseAdapter(data: MutableList<Phase>) : BaseAdapter<Phase>(data) {
    override fun layoutId(): Int {
        return R.layout.item_phase
    }
}