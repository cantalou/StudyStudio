package com.wy.studystudio.ui.me.strategy.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.wy.studystudio.BR
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentEditStrategyBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.extension.showToast
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.me.strategy.model.Phase
import com.wy.studystudio.ui.me.strategy.model.Strategy
import com.wy.studystudio.ui.me.strategy.vm.StrategyViewModel
import kotlinx.android.synthetic.main.activity_standard.*

class EditStrategyFragment : BaseFragment<FragmentEditStrategyBinding>() {

    private lateinit var vm: StrategyViewModel

    private var strategy: Strategy? = null

    private val phaseLiveData = MutableLiveData<List<Phase>>()

    override fun layoutId(): Int {
        return R.layout.fragment_edit_strategy
    }

    override fun title(): String {
        return "编辑策略"
    }

    override fun initData() {
        vm = gvm(StrategyViewModel::class.java)
        arguments?.getLong("strategyId")?.let {
            strategy = vm.get(it)
        }
        if (strategy == null) {
            strategy = vm.createModel()
        }
        strategy!!.phases.mutableLiveData = phaseLiveData
    }

    override fun initView(viewRoot: ViewGroup, inflater: LayoutInflater) {
        val phaseAdapter = PhaseAdapter()
        vdb.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = phaseAdapter
        }
        phaseAdapter.notifyDataSetChanged(strategy!!.phases)

        phaseLiveData.observeForever {
            phaseAdapter.notifyDataSetChanged(it)
        }

        val toolbar = requireActivity().toolbar
        toolbar.inflateMenu(R.menu.menu_finish)
        toolbar.setOnMenuItemClickListener { _ ->
            if (strategy!!.name.isEmpty()) {
                showToast("名称不能为空")
                return@setOnMenuItemClickListener false
            }
            if (strategy!!.phases.isEmpty()) {
                showToast("复习周期不能为空")
                return@setOnMenuItemClickListener false
            }
            vm.addOrUpdate(strategy!!)
            requireActivity().finish()
            return@setOnMenuItemClickListener true
        }

        vdb.setVariable(BR.strategy, strategy)
    }

    fun addPhase() {
        startFragment(EditPhaseFragment::class.java, 0, Bundle().apply { putLong("strategyId", strategy!!.id) })
    }
}