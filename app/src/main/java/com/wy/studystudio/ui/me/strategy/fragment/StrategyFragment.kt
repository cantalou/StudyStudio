package com.wy.studystudio.ui.me.strategy.fragment


import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentStrategyBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.fragment.ListFragment
import com.wy.studystudio.ui.me.strategy.model.Strategy
import com.wy.studystudio.ui.me.strategy.vm.StrategyViewModel

class StrategyFragment : ListFragment<FragmentStrategyBinding, Strategy, StrategyViewModel, StrategyAdapter>() {


    override fun layoutId(): Int {
        return R.layout.fragment_strategy
    }

    override fun title(): String {
        return "策略管理"
    }

    fun openAddStrategy() {
        vm.createModel()
        startFragment(EditStrategyFragment::class.java)
    }

    override fun createAdapter(): StrategyAdapter {
        return StrategyAdapter()
    }

    override fun getVM(): StrategyViewModel {
        return gvm(StrategyViewModel::class.java)
    }
}