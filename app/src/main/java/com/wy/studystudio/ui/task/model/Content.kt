package com.wy.studystudio.ui.task.model

/**
 *
 * @date    2020/11/19
 *
 */
data class Content(val id: Long, val type: Int, val taskId: Long, val content: String) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Content

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}