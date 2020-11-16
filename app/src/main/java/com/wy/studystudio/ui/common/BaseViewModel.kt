package com.wy.studystudio.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData

/**
 *
 *
 * @author  LinZhiWei
 * @date    2020/11/16
 *
 * Copyright (c) 2020年, 4399 Network CO.ltd. All Rights Reserved.
 */
open class BaseViewModel<T>(application: Application) : AndroidViewModel(application) {

    private val liveData: MutableLiveData<List<T>> = MutableLiveData()

    private val dataCached: MutableList<T> = MutableListWithLiveData(liveData)

    fun getAll(): LiveData<List<T>> {
        return liveData
    }

    fun add(strategy: T){
        dataCached.add(strategy)
    }

    fun update(strategy: T){
        dataCached.forEachIndexed{ index, item ->
            if(item == strategy){
                dataCached[index] = strategy
            }
        }
    }

    fun delete(strategy: T){
        dataCached.remove(strategy)
    }

    fun saveAll(data: List<T>) {
        dataCached.clear()
        dataCached.addAll(data)
    }
}