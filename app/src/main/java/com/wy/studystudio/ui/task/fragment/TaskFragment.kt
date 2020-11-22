package com.wy.studystudio.ui.task.fragment

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun initView(viewRoot: ViewGroup) {
        vdb.root.findViewById<RecyclerView>(com.wy.studystudio.R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }
        adapter.notifyDataSetChanged(vm.getReadyTask())
        vm.getAllLiveData().observeForever {
            adapter.notifyDataSetChanged(vm.getReadyTask())
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged(vm.getReadyTask())
    }
}