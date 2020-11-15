package com.wy.studystudio.ui.strategy.fragment


import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentStrategyBinding
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.BaseFragment
import com.wy.studystudio.ui.strategy.StrategyViewModel

class StrategyFragment : BaseFragment<FragmentStrategyBinding>() {

    lateinit var vm: StrategyViewModel

    override fun layoutId(): Int {
        return R.layout.fragment_strategy
    }

    override fun title(): String {
        return "策略管理"
    }

    override fun initView(content: ViewGroup) {
        val manager = LinearLayoutManager(context)
        vdb.recyclerView.apply {
            layoutManager = manager

        }
    }

    override fun initData() {
        vm = ViewModelProvider(this).get(StrategyViewModel::class.java)
    }

    fun openAddStrategy() {
        startFragment(AddStrategyFragment::class.java)
    }
}