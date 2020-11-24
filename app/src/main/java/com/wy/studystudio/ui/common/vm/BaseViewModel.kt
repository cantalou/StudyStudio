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

    private val modelClazz: Class<T> by lazy {
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
            val allData = repository.getAll()
            if (allData.isNotEmpty()) {
                dataCached.addAll(allData)
            }
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

    fun add(model: T) {
        dataCached.add(model)
        repository.saveAll(dataCached)
    }

    fun addOrUpdate(model: T) {
        if (dataCached.contains(model)) {
            update(model)
        } else {
            add(model)
        }
    }

    fun update(model: T) {
        dataCached.forEachIndexed { index, item ->
            if (item == model) {
                dataCached[index] = model
            }
        }
        repository.saveAll(dataCached)
    }

    fun delete(model: T) {
        dataCached.remove(model)
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