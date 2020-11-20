package com.wy.studystudio.ui.task.fragment

import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentTaskBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.common.fragment.ListFragment
import com.wy.studystudio.ui.strategy.fragment.StrategyAdapter
import com.wy.studystudio.ui.task.model.Task
import com.wy.studystudio.ui.task.vm.TaskViewModel

class TaskFragment : ListFragment<FragmentTaskBinding, Task, TaskViewModel, TaskAdapter>() {

    override fun layoutId(): Int {
        return R.layout.fragment_task
    }

    override fun getAdapter(): TaskAdapter {
        return TaskAdapter()
    }

    override fun getVM(): TaskViewModel {
        return gvm(TaskViewModel::class.java)
    }

    fun addTask() {

    }

}