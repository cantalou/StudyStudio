package com.wy.studystudio.ui.task.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.wy.studystudio.BR
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentContentContainerBinding
import com.wy.studystudio.extension.sp
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.task.model.Content

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月21日 23:20
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
abstract class ShowContentFragment<T : ViewDataBinding>(val adapter: ShowContentAdapter) : BaseFragment<FragmentContentContainerBinding>() {

    lateinit var content: Content

    var position: Int = 0

    var total: Int = 0

    lateinit var cvdb: T

    override fun layoutId(): Int {
        return R.layout.fragment_content_container
    }

    abstract fun contentLayoutId(): Int

    override fun initData() {
        requireArguments().apply {
            content = getParcelable<Content>("content")!!
            position = getInt("position")
            total = getInt("total")
        }
    }

    override fun initView(viewRoot: ViewGroup, inflater: LayoutInflater) {
        super.initView(viewRoot, inflater)
        vdb.setVariable(BR.position, position)
        vdb.setVariable(BR.total, total)
        vdb.setVariable(BR.config, sp)
        cvdb = DataBindingUtil.inflate(inflater, contentLayoutId(), vdb.contentContainer, true)
        viewRoot.viewTreeObserver.addOnGlobalLayoutListener { updateCover(false) }
        vdb.reciteMode.isChecked = sp.taskReciteMode.contains(content.taskId.toString())
        vdb.reciteMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sp.taskReciteMode.add(content.taskId.toString())
            } else {
                sp.taskReciteMode.remove(content.taskId.toString())
            }
            updateCover(true)
            //adapter.notifyDataSetChanged()
        }
    }

    private fun updateCover(force: Boolean) {
        val contentCover = vdb.contentCover
        val containerHeight = vdb.contentContainer.measuredHeight
        val isReciteMode = sp.taskReciteMode.contains(content.taskId.toString())
        vdb.reciteMode.isChecked = isReciteMode
        if (containerHeight > 0 && isReciteMode) {
            Log.w("ContentImageFragment", "containerHeight: $containerHeight")
            val coverHeight = (sp.coverPercent.toDouble() / 100 * containerHeight).toInt()
            Log.w("ContentImageFragment", "coverHeight: $coverHeight")
            if (contentCover.layoutParams.height != coverHeight || force) {
                contentCover.layoutParams.height = coverHeight
                (contentCover.layoutParams as ConstraintLayout.LayoutParams).topToTop = -1
                contentCover.requestLayout()
                contentCover.visibility = View.VISIBLE
            }
        } else {
            contentCover.visibility = View.GONE
        }
    }

}