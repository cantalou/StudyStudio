package com.wy.studystudio.ui.strategy.fragment

import android.view.ViewGroup
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentEditPhaseBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.extension.showToast
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.strategy.model.Phase
import com.wy.studystudio.ui.strategy.model.Phase.CREATOR.DAY_INTERVAL
import com.wy.studystudio.ui.strategy.model.Phase.CREATOR.HOUR_INTERVAL
import com.wy.studystudio.ui.strategy.model.Phase.CREATOR.MINUTE_INTERVAL
import com.wy.studystudio.ui.strategy.vm.StrategyViewModel
import kotlinx.android.synthetic.main.activity_standard.*

class EditPhaseFragment : BaseFragment<FragmentEditPhaseBinding>() {

    lateinit var vm: StrategyViewModel

    private var phase: Phase? = null

    private var strategyId: Long = 0

    override fun layoutId(): Int {
        return R.layout.fragment_edit_phase
    }

    override fun title(): String {
        return "添加周期"
    }

    override fun initData() {
        vm = gvm(StrategyViewModel::class.java)
        arguments?.apply {
            phase = getParcelable<Phase>("phase")
            strategyId = getLong("strategyId")
        }
        if (phase == null) {
            phase = Phase(0, 0, strategyId)
        }
    }

    override fun initView(content: ViewGroup) {

        phase?.apply {
            var interval = this.interval

            vdb.days.setSelection((interval / DAY_INTERVAL).toInt())

            interval %= DAY_INTERVAL
            vdb.hour.setSelection((interval / HOUR_INTERVAL).toInt())

            interval %= HOUR_INTERVAL
            vdb.minute.setSelection((interval / (5 * MINUTE_INTERVAL)).toInt())

        }

        val toolbar = requireActivity().toolbar
        toolbar.inflateMenu(R.menu.menu_finish)
        toolbar.setOnMenuItemClickListener { _ ->
            addPhase()
            return@setOnMenuItemClickListener true
        }
    }

    private fun addPhase() {
        var interval = 0L
        interval += vdb.days.selectedItemPosition * DAY_INTERVAL
        interval += vdb.hour.selectedItemPosition * HOUR_INTERVAL
        interval += vdb.minute.selectedItemPosition * 5 * MINUTE_INTERVAL
        if (interval == 0L) {
            showToast("周期不能为空")
            return
        }
        phase!!.interval = interval
        vm.addOrUpdate(phase!!)
        activity?.finish()
    }
}