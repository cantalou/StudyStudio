package com.wy.studystudio.ui.task.fragment

import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentTaskBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.fragment.ListFragment
import com.wy.studystudio.ui.task.model.Task
import com.wy.studystudio.ui.task.vm.TaskViewModel

class TaskFragment : ListFragment<FragmentTaskBinding, Task, TaskViewModel, TaskAdapter>() {

    override fun layoutId(): Int {
        return R.layout.fragment_task
    }

    override fun createAdapter(): TaskAdapter {
        return TaskAdapter()
    }

    override fun getVM(): TaskViewModel {
        return gvm(TaskViewModel::class.java)
    }

    fun addTask() {
        startFragment(EditTaskFragment::class.java)
    }

}