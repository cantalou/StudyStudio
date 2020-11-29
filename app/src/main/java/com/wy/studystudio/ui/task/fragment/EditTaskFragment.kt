package com.wy.studystudio.ui.task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.wy.studystudio.BR
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentEditTaskBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.extension.showToast
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.task.model.Content
import com.wy.studystudio.ui.task.model.Task
import com.wy.studystudio.ui.task.vm.TaskViewModel
import kotlinx.android.synthetic.main.activity_standard.*

/**
 *
 * @author  cantalou
 * @date    2020/11/20
 *
 * Copyright (c) 2020年, WY Network CO.ltd. All Rights Reserved.
 */
class EditTaskFragment : BaseFragment<FragmentEditTaskBinding>() {

    private lateinit var vm: TaskViewModel

    private var task: Task? = null

    private val contentLD = MutableLiveData<List<Content>>()

    override fun layoutId(): Int {
        return R.layout.fragment_edit_task
    }

    override fun title(): String {
        return "编辑任务"
    }

    override fun initData() {
        vm = gvm(TaskViewModel::class.java)
        arguments?.getLong("taskId")?.takeIf { it > 0 }?.let {
            task = vm.get(it)
        }
        if (task == null) {
            task = vm.createModel()
            task!!.nextTime = System.currentTimeMillis()
        }
        task!!.contents.mutableLiveData = contentLD
        vdb.setVariable(BR.task, task)
    }

    override fun initView(viewRoot: ViewGroup, inflater: LayoutInflater) {
        super.initView(viewRoot, inflater)
        val contentAdapter = ContentAdapter()
        vdb.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contentAdapter
        }
        contentAdapter.notifyDataSetChanged(task!!.contents)

        contentLD.observeForever {
            contentAdapter.notifyDataSetChanged(it)
        }

        val toolbar = requireActivity().toolbar
        toolbar.inflateMenu(R.menu.menu_finish)
        toolbar.setOnMenuItemClickListener { _ ->
            if (task!!.name.isEmpty()) {
                showToast("名称不能为空")
                return@setOnMenuItemClickListener false
            }
            if (task!!.contents.isEmpty()) {
                showToast("复习内容不能为空")
                return@setOnMenuItemClickListener false
            }
            vm.addOrUpdate(task!!)
            requireActivity().finish()
            return@setOnMenuItemClickListener true
        }
    }


    fun addContent() {
        startFragment(EditContentFragment::class.java, 0, Bundle().apply { putLong("taskId", task!!.id) })
    }

}