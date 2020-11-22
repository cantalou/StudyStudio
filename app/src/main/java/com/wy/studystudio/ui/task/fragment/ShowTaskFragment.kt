package com.wy.studystudio.ui.task.fragment

import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentShowTaskBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.strategy.vm.StrategyViewModel
import com.wy.studystudio.ui.task.model.Task
import com.wy.studystudio.ui.task.vm.TaskViewModel
import kotlinx.android.synthetic.main.activity_standard.*


/**
 *
 * @author  cantalou
 * @date    2020年11月21日 22:58
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ShowTaskFragment : BaseFragment<FragmentShowTaskBinding>() {

    lateinit var strategyVM: StrategyViewModel

    lateinit var taskVM: TaskViewModel

    lateinit var task: Task

    override fun layoutId(): Int {
        return R.layout.fragment_show_task
    }

    override fun initData() {
        strategyVM = gvm(StrategyViewModel::class.java)
        taskVM = gvm(TaskViewModel::class.java)
        task = requireArguments().getParcelable<Task>("task")!!
    }

    override fun initView(viewRoot: ViewGroup) {
        super.initView(viewRoot)
        val showContentAdapter = ShowContentAdapter(this, task)
        vdb.viewPager.apply {
            offscreenPageLimit = 2
            adapter = showContentAdapter
        }
        requireActivity().toolbar.visibility = View.GONE
        val layoutParams = requireActivity().container.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.topToTop = R.id.parent
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onDestroy() {
        if (vdb.viewPager.currentItem == task.contents.size - 1) {
            task.finishTime = System.currentTimeMillis()
            val phases = strategyVM.get(task.strategyId).phases
            phases.forEachIndexed { index, phase ->
                if (phase.id == task.phaseId) {
                    if (index == phases.size - 1) {
                        task.phaseId = Long.MAX_VALUE
                    } else {
                        task.phaseId = phases[index + 1].id
                    }
                    taskVM.update(task)
                    return@forEachIndexed
                }
            }
        }
        super.onDestroy()
    }
}