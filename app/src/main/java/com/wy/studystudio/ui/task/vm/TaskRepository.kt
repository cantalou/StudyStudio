package com.wy.studystudio.ui.task.vm

import android.content.Context
import androidx.core.content.edit
import com.wy.studystudio.ui.common.vm.BaseRepository
import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData
import com.wy.studystudio.ui.task.model.Content
import com.wy.studystudio.ui.task.model.Task
import org.json.JSONArray
import org.json.JSONObject

/**
 *
 *
 * @author  cantalou
 * @date    2020/11/19
 *
 * Copyright (c) 2020年, WY Network CO.ltd. All Rights Reserved.
 */
class TaskRepository(private val context: Context) : BaseRepository<Task> {

    override fun getAll(): MutableList<Task> {
        val taskJson = context.getSharedPreferences("Task", Context.MODE_PRIVATE).getString("Task", "[]")
        val taskJA = JSONArray(taskJson)
        val data = mutableListOf<Task>()
        for (index in 0 until taskJA.length()) {

            val taskJ = taskJA[index] as JSONObject

            val contents = MutableListWithLiveData<Content>()
            val contentJA = taskJ.getJSONArray("contents")
            for (index in 0 until contentJA.length()) {
                val contentJ = contentJA[index] as JSONObject
                val content = Content(contentJ.getLong("id"), contentJ.getInt("type"), contentJ.getLong("taskId"), contentJ.getString("content"))
                contents.add(content)
            }

            val strategy =
                Task(taskJ.getLong("id"), taskJ.getString("name"), taskJ.getLong("strategyId"), taskJ.getLong("phaseId"), taskJ.getLong("finishTime"), contents)
            data.add(strategy)
        }
        return data
    }

    override fun saveAll(data: List<Task>) {
        val taskJA = JSONArray()
        data.forEachIndexed { index, task ->
            taskJA.put(index, task2Json(task))
        }
        context.getSharedPreferences("Task", Context.MODE_PRIVATE).edit {
            putString("Task", taskJA.toString())
        }
    }

    private fun task2Json(task: Task): JSONObject {
        val taskJ = JSONObject()
        taskJ.put("id", task.id)
        taskJ.put("name", task.name)
        taskJ.put("strategyId", task.strategyId)
        taskJ.put("phaseId", task.phaseId)
        taskJ.put("finishTime", task.finishTime)

        val contentJA = JSONArray()
        task.contents.forEachIndexed { index, content ->
            contentJA.put(index, content2Json(content))
        }
        taskJ.put("contents", contentJA)
        return taskJ
    }

    private fun content2Json(content: Content): JSONObject {
        val contentJ = JSONObject()
        contentJ.put("id", content.id)
        contentJ.put("type", content.type)
        contentJ.put("taskId", content.taskId)
        contentJ.put("content", content.content)
        return contentJ
    }
}