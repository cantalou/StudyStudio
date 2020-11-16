package com.wy.studystudio.ui.strategy.fragment

import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentEditPhaseBinding
import com.wy.studystudio.ui.common.BaseFragment
import com.wy.studystudio.ui.strategy.vm.PhaseViewModel
import com.wy.studystudio.ui.strategy.vm.StrategyViewModel
import kotlinx.android.synthetic.main.activity_standard.*

class EditPhaseFragment : BaseFragment<FragmentEditPhaseBinding>() {

    lateinit var strategyViewModel: StrategyViewModel

    lateinit var phaseViewModel: PhaseViewModel

    override fun layoutId(): Int {
        return R.layout.fragment_edit_phase
    }

    override fun title(): String {
        return "添加周期"
    }

    override fun initData() {
        phaseViewModel = ViewModelProvider(this).get(PhaseViewModel::class.java)
        strategyViewModel = ViewModelProvider(this).get(StrategyViewModel::class.java)
    }

    override fun initView(content: ViewGroup) {
        val toolbar = requireActivity().toolbar
        toolbar.inflateMenu(R.menu.menu_finish)
        toolbar.setOnMenuItemClickListener { menu ->
            return@setOnMenuItemClickListener true
        }

        val dayItem = resources.getIntArray(R.array.hour)
        vdb.days.setOnItemClickListener { parent, view, position, id ->

        }

        val hourItem = resources.getIntArray(R.array.hour)
        vdb.days.setOnItemClickListener { parent, view, position, id ->

        }

        val minuteItem = resources.getIntArray(R.array.hour)
        vdb.days.setOnItemClickListener { parent, view, position, id ->

        }
    }

    fun addPhase() {

    }

    fun finish() {

    }
}