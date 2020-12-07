package com.wy.studystudio.ui.common.fragment

import android.view.LayoutInflater
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

    lateinit var adapter: A

    abstract fun createAdapter(): A

    abstract fun getVM(): M

    override fun initData() {
        vm = getVM()
    }

    override fun initView(viewRoot: ViewGroup, inflater: LayoutInflater) {
        adapter = createAdapter()
        val recyclerView = vdb.root.findViewById<RecyclerView>(com.wy.studystudio.R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        loadData()
    }

    open fun loadData() {
        vm.getAll()
        vm.getAllLiveData().observeForever {
            adapter.notifyDataSetChanged(it)
        }
    }
}