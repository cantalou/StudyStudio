package com.wy.studystudio.ui.strategy.fragment

import android.os.Parcelable
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentEditStrategyBinding
import com.wy.studystudio.extension.d
import com.wy.studystudio.extension.log
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.BaseFragment
import com.wy.studystudio.ui.strategy.model.Strategy
import com.wy.studystudio.ui.strategy.vm.StrategyViewModel
import kotlinx.android.synthetic.main.activity_standard.*

class EditStrategyFragment : BaseFragment<FragmentEditStrategyBinding>() {

    lateinit var vm: StrategyViewModel

    var strategy: Strategy = Strategy(System.currentTimeMillis(), "")

    override fun layoutId(): Int {
        return R.layout.fragment_edit_strategy
    }

    override fun title(): String {
        return "添加策略"
    }

    override fun initData() {
        vm = ViewModelProvider(this).get(StrategyViewModel::class.java)
        arguments?.get("strategy")?.let {
            strategy = it as Strategy
        }
    }

    override fun initView(content: ViewGroup) {
        vdb.recyclerView.layoutManager = LinearLayoutManager(context)
        val phaseAdapter = PhaseAdapter()
        vdb.recyclerView.adapter = phaseAdapter
        phaseAdapter.notifyDataSetChanged(strategy.phases)
        requireActivity().toolbar.inflateMenu(R.menu.menu_finish)
    }

    fun addPhase() {
        startFragment(EditPhaseFragment::class.java, 0, mapOf<String, Parcelable>("strategy" to strategy))
    }
}