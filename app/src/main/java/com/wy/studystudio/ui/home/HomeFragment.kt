package com.wy.studystudio.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var adapter: FunctionAdapter

    private lateinit var vdb: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        vdb = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        adapter = FunctionAdapter(mutableListOf())
        vdb.functions.layoutManager = GridLayoutManager(context, 3)
        vdb.functions.adapter = adapter
        showFunction()
        return vdb.root
    }

    private fun showFunction() {
        launch {
            val data = homeViewModel.getFunctions()
            adapter.data.apply {
                clear()
                addAll(data)
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}