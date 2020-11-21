package com.wy.studystudio.ui.task.model

import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData
import com.wy.studystudio.ui.common.model.BaseModel

/**
 *
 * @date    2020/11/19
 *
 */
class Task(
    id: Long = 0,
    val name: String = "",
    val strategyId: Long = 0,
    val phaseId: Long = 0,
    val finishTime: Long = 0,
    var contents: MutableListWithLiveData<Content> = MutableListWithLiveData()
) : BaseModel(id) {
    constructor() : this(0)
}