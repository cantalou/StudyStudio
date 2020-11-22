package com.wy.studystudio.ui.common.fragment

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wy.studystudio.ui.common.adapter.BaseAdapter
import com.wy.studystudio.ui.common.model.BaseModel
import com.wy.studystudio.ui.common.vm.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 *
 *
 * @author  cantalou
 * @date    2020/11/20
 *
 * Copyright (c) 2020年, WY Network CO.ltd. All Rights Reserved.
 */
abstract class ListFragment<V : ViewDataBinding, T : BaseModel, M : BaseViewModel<T>, A : BaseAdapter<T>>() : BaseFragment<V>() {

    lateinit var vm: M

    val adapter: BaseAdapter<T> by lazy { createAdapter() }

    abstract fun createAdapter(): A

    abstract fun getVM(): M

    override fun initData() {
        (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        vm = getVM()
    }

    override fun initView(viewRoot: ViewGroup) {
        vdb.root.findViewById<RecyclerView>(com.wy.studystudio.R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }
        adapter.notifyDataSetChanged(vm.getAll())
        vm.getAllLiveData().observeForever {
            adapter.notifyDataSetChanged(it)
        }
    }
}