package com.wy.studystudio.ui.strategy.fragment

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.wy.studystudio.R
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.adapter.BaseAdapter
import com.wy.studystudio.ui.strategy.model.Phase

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 23:13
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class PhaseAdapter : BaseAdapter<Phase>() {

    lateinit var cnNumber: Array<CharSequence>

    override fun layoutId(): Int {
        return R.layout.item_phase
    }

    fun edit(phase: Phase) {
        context.startFragment(EditPhaseFragment::class.java, 0, Bundle().apply {
            putParcelable("phase", phase)
        })
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        cnNumber = context.resources.getTextArray(R.array.cn_number)
    }

    fun itemTitle(phase: Phase, position: Int): String {
        return context.getString(R.string.phase_title, cnNumber[position], phase.desc())
    }
}