package com.wy.studystudio.ui.task.vm

import android.app.Application
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.ui.common.vm.BaseViewModel
import com.wy.studystudio.ui.strategy.vm.StrategyViewModel
import com.wy.studystudio.ui.task.model.Task

class TaskViewModel(app: Application) : BaseViewModel<Task>(app, TaskRepository(app)) {

    private val strategyVM: StrategyViewModel = gvm(StrategyViewModel::class.java)

    fun getReadyTask(): List<Task> {
        return getAll().filter { task ->
            val phase = strategyVM.get(task.strategyId).phases.find { it.id == task.phaseId } ?: return@filter false
            return@filter (System.currentTimeMillis() - task.finishTime) > phase!!.interval
        }
    }
}