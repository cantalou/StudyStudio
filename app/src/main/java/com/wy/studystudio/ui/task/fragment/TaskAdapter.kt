package com.wy.studystudio.ui.task.fragment

import android.os.Bundle
import android.view.View
import com.wy.studystudio.R
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.adapter.BaseAdapter
import com.wy.studystudio.ui.task.model.Task

/**
 *
 * @author  cantalou
 * @date    2020年11月15日 22:50
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class TaskAdapter : BaseAdapter<Task>() {

    override fun layoutId(): Int {
        return R.layout.item_task
    }

    fun show(view: View, task: Task) {
        view.context.startFragment(ShowTaskFragment::class.java, 0, Bundle().apply { putParcelable("task", task) })
    }

    fun edit(view: View, task: Task) {
        view.context.startFragment(EditTaskFragment::class.java, 0, Bundle().apply { putLong("taskId", task.id) })
    }
}