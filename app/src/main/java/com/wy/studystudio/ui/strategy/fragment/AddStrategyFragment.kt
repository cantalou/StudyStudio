package com.wy.studystudio.ui.strategy.fragment

import androidx.lifecycle.ViewModelProvider
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentAddStrategyBinding
import com.wy.studystudio.ui.common.BaseFragment
import com.wy.studystudio.ui.strategy.StrategyViewModel

class AddStrategyFragment : BaseFragment<FragmentAddStrategyBinding>() {

    lateinit var vm: StrategyViewModel

    override fun layoutId(): Int {
        return R.layout.fragment_add_strategy
    }

    override fun title(): String {
        return "添加策略"
    }

    override fun initData() {
        vm = ViewModelProvider(this).get(StrategyViewModel::class.java)
    }

    fun addItem() {

    }
}