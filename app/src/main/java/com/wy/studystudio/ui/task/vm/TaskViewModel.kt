package com.wy.studystudio.ui.task.vm

import android.app.Application
import android.text.format.DateUtils
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.ui.common.vm.BaseViewModel
import com.wy.studystudio.ui.me.strategy.vm.StrategyViewModel
import com.wy.studystudio.ui.task.model.Task


class TaskViewModel(app: Application) : BaseViewModel<Task>(app, TaskRepository(app)) {

    val strategyVM  = gvm(StrategyViewModel::class.java)

    fun handleTaskFinish(task: Task) {
        task.finishTime = System.currentTimeMillis()
        val phases = strategyVM.get(task.strategyId).phases
        phases.forEachIndexed { index, phase ->
            if (phase.id == task.phaseId) {
                if (index == phases.size - 1) {
                    task.phaseId = Long.MAX_VALUE
                } else {
                    task.phaseId = phases[index + 1].id
                }
                task.nextTime = task.finishTime + phases[index + 1].interval
                update(task)
                return@handleTaskFinish
            }
        }
    }
}