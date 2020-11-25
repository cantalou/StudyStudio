package com.wy.studystudio.ui.task.fragment

import android.os.Bundle
import android.text.TextUtils
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.wy.studystudio.BR
import com.wy.studystudio.R
import com.wy.studystudio.databinding.ItemTaskTitleBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.extension.startFragment
import com.wy.studystudio.ui.common.adapter.BaseAdapter
import com.wy.studystudio.ui.common.adapter.DBViewHolder
import com.wy.studystudio.ui.strategy.vm.StrategyViewModel
import com.wy.studystudio.ui.task.model.Task
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 *
 * @author  cantalou
 * @date    2020年11月15日 22:50
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class TaskAdapter : BaseAdapter<Task>() {

    private val reviewTimeFormat = SimpleDateFormat("yyyy年MM月dd日 HH:mm")

    private val reviewDayFormat = SimpleDateFormat("yyyy年MM月dd日")

    override fun layoutId(): Array<Int> {
        return arrayOf(R.layout.item_task)
    }

    fun show(view: View, task: Task) {
        view.context.startFragment(ShowTaskFragment::class.java, 0, Bundle().apply { putParcelable("task", task) })
    }

    fun edit(view: View, task: Task) {
        view.context.startFragment(EditTaskFragment::class.java, 0, Bundle().apply { putLong("taskId", task.id) })
    }

    fun reviewTime(task: Task): String {
        val now = System.currentTimeMillis()
        var reviewTime = task.nextTime
        if (reviewTime < now) {
            reviewTime = now
        }
        return reviewTimeFormat.format(Date(reviewTime))
    }

    override fun notifyDataSetChanged(newData: List<Task>) {
        data.clear()
        data.addAll(newData)
        data.sortBy { it.nextTime }
        run loop@{
            data.forEachIndexed { index, task ->
                if (task.nextTime > 0 && !DateUtils.isToday(task.nextTime)) {
                    data.add(index, Task(-1, "未来任务"))
                    return@loop
                }
            }
        }
        data.add(0, Task(-1, "今日任务"))
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position].id) {
            -1L -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> DBViewHolder(DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.item_task_title, parent, false))
            else -> DBViewHolder(DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.item_task, parent, false))
        }
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        holder.vdb.setVariable(BR.item, data[position])
        holder.vdb.setVariable(BR.adapter, this)
        holder.vdb.setVariable(BR.position, position)
        if (getItemViewType(position) == 0) {
            (holder.vdb as ItemTaskTitleBinding).lineBar.visibility = if (position > 0) View.VISIBLE else View.GONE
            (holder.vdb as ItemTaskTitleBinding).tips.visibility = if ((position + 1 == data.size || data[position + 1].id < 0)) View.VISIBLE else View.GONE
        }
    }
}