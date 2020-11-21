package com.wy.studystudio.ui.common.model

/**
 *
 *
 * @date    2020/11/19
 *
 */
open class BaseModel(var id: Long) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseModel) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}