package com.wy.studystudio.ui.strategy.fragment


import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentStrategyBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.adapter.BaseAdapter
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.common.fragment.ListFragment
import com.wy.studystudio.ui.common.vm.BaseViewModel
import com.wy.studystudio.ui.strategy.model.Strategy
import com.wy.studystudio.ui.strategy.vm.StrategyViewModel

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

    override fun getAdapter(): StrategyAdapter {
        return StrategyAdapter()
    }

    override fun getVM(): StrategyViewModel {
        return gvm(StrategyViewModel::class.java)
    }
}