package com.wy.studystudio.ui.task.vm

import android.app.Application
import com.wy.studystudio.ui.common.vm.BaseViewModel
import com.wy.studystudio.ui.task.model.Task

class TaskViewModel(app: Application) : BaseViewModel<Task>(app, TaskRepository(app)) {

}