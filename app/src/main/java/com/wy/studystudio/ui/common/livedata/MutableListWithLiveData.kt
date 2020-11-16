package com.wy.studystudio.ui.common.livedata

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import java.util.function.Predicate

/**
 *
 * @author  LinZhiWei
 * @date    2020/11/16
 *
 * Copyright (c) 2020年, 4399 Network CO.ltd. All Rights Reserved.
 */
class MutableListWithLiveData<T>(private val mutableLiveData: MutableLiveData<List<T>>) : ArrayList<T>() {

    override fun addAll(elements: Collection<T>): Boolean {
        val addAll = super.addAll(elements)
        mutableLiveData.value = this
        return addAll
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val addAll = super.addAll(index, elements)
        mutableLiveData.value = this
        return addAll
    }

    override fun add(element: T): Boolean {
        val add = super.add(element)
        mutableLiveData.value = this
        return add
    }

    override fun add(index: Int, element: T) {
        super.add(index, element)
        mutableLiveData.value = this
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        mutableLiveData.value = this
    }

    override fun removeAt(index: Int): T {
        val removeAt = super.removeAt(index)
        mutableLiveData.value = this
        return removeAt
    }

    override fun remove(element: T): Boolean {
        val remove = super.remove(element)
        mutableLiveData.value = this
        return remove
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun removeIf(filter: Predicate<in T>): Boolean {
        val removeIf = super.removeIf(filter)
        mutableLiveData.value = this
        return removeIf
    }
}