package com.wy.studystudio.ui.task.fragment

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentEditContentBinding
import com.wy.studystudio.extension.gvm
import com.wy.studystudio.ui.common.fragment.BaseFragment
import com.wy.studystudio.ui.task.model.Content
import com.wy.studystudio.ui.task.vm.TaskViewModel
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


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

        const val DATA_REQUEST_CODE_TAKE_PHOTO = 1

        const val DATA_REQUEST_CODE_VIDEO = 2

        const val DATA_REQUEST_CODE_IMAGE = 3
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

    override fun initView(viewRoot: ViewGroup, inflater: LayoutInflater) {
        super.initView(viewRoot, inflater)
    }

    fun openFileChoose() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        this.startActivityForResult(intent, DATA_REQUEST_CODE_FILE)
    }

    fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val filename: String = SimpleDateFormat("yyyyMMdd-HHmmss").format(Date()).toString() + ".png"
        val activity = requireActivity()
        val file = File(activity.getExternalFilesDir("camera"), filename)
        content!!.content = file.absolutePath
        lateinit var fileUri: Uri
        fileUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(activity, "${activity.packageName}.SSFileProvider", file)
        } else {
            Uri.fromFile(file)
        }
        takePictureIntent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        startActivityForResult(takePictureIntent, DATA_REQUEST_CODE_TAKE_PHOTO)
    }

    fun openVideoChoose() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "video/*"
        this.startActivityForResult(intent, DATA_REQUEST_CODE_VIDEO)
    }

    fun openImageChoose() {
        Matisse.from(this)
            .choose(MimeType.ofImage())
            .countable(true)
            .maxSelectable(100)
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
            .thumbnailScale(0.85f)
            .imageEngine(GlideEngine())
            .spanCount(4)
            .forResult(DATA_REQUEST_CODE_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

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
            DATA_REQUEST_CODE_TAKE_PHOTO -> {
                content!!.type = Content.TYPE_VIDEO
            }

            DATA_REQUEST_CODE_VIDEO -> {
                content!!.type = Content.TYPE_VIDEO
                content!!.content = data!!.data.toString()
            }

            DATA_REQUEST_CODE_IMAGE -> {
                contents.remove(content!!)
                val selected = Matisse.obtainResult(data)
                for (uri in selected) {
                    val newContent = Content(contents.size + 1L, Content.TYPE_IMAGE, content!!.taskId, uri.toString())
                    contents.add(newContent)
                }
            }
        }
        requireActivity().finish()
    }
}