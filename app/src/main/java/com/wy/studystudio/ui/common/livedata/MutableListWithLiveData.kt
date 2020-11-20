package com.wy.studystudio.ui.common.livedata

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import java.util.function.Predicate

/**
 *
 * @author  cantalou
 * @date    2020/11/16
 *
 */
class MutableListWithLiveData<T> : ArrayList<T> {

    var mutableLiveData: MutableLiveData<List<T>>? = null

    constructor(initialCapacity: Int) : super(initialCapacity)
    constructor() : super()
    constructor(c: MutableCollection<out T>) : super(c)
    constructor(liveData: MutableLiveData<List<T>>) {
        mutableLiveData = liveData
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val addAll = super.addAll(elements)
        mutableLiveData?.value = this
        return addAll
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val addAll = super.addAll(index, elements)
        mutableLiveData?.value = this
        return addAll
    }

    override fun add(element: T): Boolean {
        val add = super.add(element)
        mutableLiveData?.value = this
        return add
    }

    override fun add(index: Int, element: T) {
        super.add(index, element)
        mutableLiveData?.value = this
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        mutableLiveData?.value = this
    }

    override fun removeAt(index: Int): T {
        val removeAt = super.removeAt(index)
        mutableLiveData?.value = this
        return removeAt
    }

    override fun remove(element: T): Boolean {
        val remove = super.remove(element)
        mutableLiveData?.value = this
        return remove
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun removeIf(filter: Predicate<in T>): Boolean {
        val removeIf = super.removeIf(filter)
        mutableLiveData?.value = this
        return removeIf
    }

    override fun set(index: Int, element: T): T {
        return super.set(index, element)
        mutableLiveData?.value = this
    }
}