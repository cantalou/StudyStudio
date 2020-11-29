package com.wy.studystudio.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentMeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MeFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var meViewModel: MeViewModel

    private lateinit var adapter: FunctionAdapter

    private lateinit var vdb: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        meViewModel = ViewModelProvider(this).get(MeViewModel::class.java)
        vdb = DataBindingUtil.inflate<FragmentMeBinding>(
            inflater,
            R.layout.fragment_me,
            container,
            false
        )
        adapter = FunctionAdapter()
        vdb.functions.layoutManager = GridLayoutManager(context, 3)
        vdb.functions.adapter = adapter
        showFunction()
        return vdb.root
    }

    private fun showFunction() {
        launch {
            val data = meViewModel.getFunctions()
            adapter.notifyDataSetChanged(data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}