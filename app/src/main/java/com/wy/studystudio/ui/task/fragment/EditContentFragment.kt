package com.wy.studystudio.ui.task.fragment

import android.content.Intent
import android.view.ViewGroup
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentEditContentBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.task.model.Content
import com.wy.studystudio.ui.task.vm.TaskViewModel


/**
 *
 * @author  cantalou
 * @date    2020/11/20
 *
 * Copyright (c) 2020年, WY Network CO.ltd. All Rights Reserved.
 */
class EditContentFragment : BaseFragment<FragmentEditContentBinding>() {

    companion object {

        const val DATA_REQUEST_CODE_FILE = 0;

        const val DATA_REQUEST_CODE_CAPTURE = 1

        const val DATA_REQUEST_CODE_VIDEO = 2

        const val DATA_REQUEST_CODE_IMAGE = 2
    }

    lateinit var vm: TaskViewModel

    var content: Content? = null

    private var taskId: Long = 0

    override fun title(): String {
        return "编辑复习内容"
    }

    override fun layoutId(): Int {
        return R.layout.fragment_edit_content
    }

    override fun initData() {
        vm = gvm(TaskViewModel::class.java)
        arguments?.apply {
            content = getParcelable<Content>("content")
            taskId = getLong("taskId")
        }
        if (content == null) {
            content = Content(0, 0, taskId, "")
        }
    }

    override fun initView(content: ViewGroup) {
        super.initView(content)
    }

    fun openFileChoose() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        this.startActivityForResult(intent, DATA_REQUEST_CODE_FILE)
    }

    fun openCamera() {

    }

    fun openVideoChoose() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "video/*"
        this.startActivityForResult(intent, DATA_REQUEST_CODE_VIDEO)
    }

    fun openImageChoose() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        this.startActivityForResult(intent, DATA_REQUEST_CODE_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val contents = vm.get(content!!.taskId).contents
        if (content!!.id == 0L) {
            content!!.id = contents.size + 1L
            contents.add(content!!)
        }

        when (requestCode) {
            DATA_REQUEST_CODE_FILE -> {
                content!!.type = Content.TYPE_TEXT
                content!!.content = data!!.data.toString()
            }

            DATA_REQUEST_CODE_IMAGE -> {
                content!!.type = Content.TYPE_IMAGE
                content!!.content = data!!.data.toString()
            }

            DATA_REQUEST_CODE_VIDEO -> {
                content!!.type = Content.TYPE_VIDEO
                content!!.content = data!!.data.toString()
            }
        }

        requireActivity().finish()
    }
}