package com.wy.studystudio.ui.common.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData
import com.wy.studystudio.ui.common.model.BaseModel
import java.lang.reflect.ParameterizedType

/**
 *
 * @author  cantalou
 * @date    2020/11/16
 *
 */
abstract class BaseViewModel<T : BaseModel>(application: Application, val repository: BaseRepository<T>) : AndroidViewModel(application) {

    private val modelClazz:Class<T> by lazy {
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>)
    }

    protected var newModel: T? = null

    private val liveData: MutableLiveData<List<T>> = MutableLiveData()

    protected val dataCached: MutableListWithLiveData<T> = MutableListWithLiveData(liveData)

    fun getAllLiveData(): LiveData<List<T>> {
        return liveData
    }

    open fun getAll(): MutableList<T> {
        if (dataCached.isEmpty()) {
            dataCached.addAll(repository.getAll())
            return dataCached
        }
        return dataCached
    }

    open fun get(id: Long): T {
        getAll()
        if (id == newModel?.id) {
            return newModel!!
        }
        return dataCached.find { it.id == id }!!
    }

    fun add(strategy: T) {
        dataCached.add(strategy)
        repository.saveAll(dataCached)
    }

    fun addOrUpdate(strategy: T) {
        if (dataCached.contains(strategy)) {
            update(strategy)
        } else {
            add(strategy)
        }
    }

    fun update(strategy: T) {
        dataCached.forEachIndexed { index, item ->
            if (item == strategy) {
                dataCached[index] = strategy
            }
        }
        repository.saveAll(dataCached)
    }

    fun delete(strategy: T) {
        dataCached.remove(strategy)
        repository.saveAll(dataCached)
    }

    fun saveAll(data: List<T>) {
        dataCached.clear()
        dataCached.addAll(data)
        repository.saveAll(dataCached)
    }

    fun createModel(): T {
        val newId = if (dataCached.size > 0) dataCached[dataCached.size - 1].id + 1 else 1L
        newModel = modelClazz.newInstance()
        newModel!!.id = newId
        return newModel!!
    }
}