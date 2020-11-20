package com.wy.studystudio.ui.task.model

import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData

/**
 *
 * @date    2020/11/19
 *
 */
data class Task(val id: Long,val name:String, val strategyId: Long, val phaseId: Long, val finishTime: Long, var contents: MutableListWithLiveData<Content>) {
}