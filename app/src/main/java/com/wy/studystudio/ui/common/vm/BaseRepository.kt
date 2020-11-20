package com.wy.studystudio.ui.common.vm

/**
 *
 *
 * @date    2020/11/18
 *
 */
interface BaseRepository<T> {

    fun getAll(): MutableList<T>

    fun saveAll(data: List<T>)

    fun add(strategy: T) {
        getAll().apply {
            add(strategy)
            saveAll(this)
        }
    }

    fun update(strategy: T) {
        getAll().apply {
            remove(strategy)
            saveAll(this)
        }
    }

    fun delete(strategy: T) {
        getAll().apply {
            remove(strategy)
            saveAll(this)
        }
    }
}