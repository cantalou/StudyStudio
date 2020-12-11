package com.wy.studystudio.ui.task.fragment

import android.media.MediaPlayer
import android.media.MediaPlayer.MEDIA_ERROR_IO
import android.media.MediaPlayer.MEDIA_ERROR_MALFORMED
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentContentImageBinding
import com.wy.studystudio.databinding.FragmentContentLinkBinding
import com.wy.studystudio.databinding.FragmentContentTextBinding
import com.wy.studystudio.databinding.FragmentContentVideoBinding
import com.wy.studystudio.extension.showToast
import com.wy.studystudio.extension.v
import com.wy.studystudio.ui.common.fragment.BaseFragment

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月21日 23:20
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ContentVideoFragment(adapter: ShowContentAdapter) : ShowContentFragment<FragmentContentVideoBinding>(adapter) {

    val errorMap = mapOf<Int, String>(
        MEDIA_ERROR_IO to "MEDIA_ERROR_IO",
        MEDIA_ERROR_MALFORMED to "MEDIA_ERROR_MALFORMED",
        MEDIA_ERROR_ to "MEDIA_ERROR_IO",
        MEDIA_ERROR_IO to "MEDIA_ERROR_IO",
        MEDIA_ERROR_IO to "MEDIA_ERROR_IO",
        MEDIA_ERROR_IO to "MEDIA_ERROR_IO",
        MEDIA_ERROR_MALFORMED to "MEDIA_ERROR_MALFORMED"
    )

    override fun contentLayoutId(): Int {
        return R.layout.fragment_content_video
    }

    override fun initView(viewRoot: ViewGroup, inflater: LayoutInflater) {
        super.initView(viewRoot, inflater)
        cvdb.video.apply {
            setMediaController(MediaController(requireActivity()))
            setVideoURI(Uri.parse(content.content))
            setOnErrorListener { mp, what, extra ->
                showToast("Can not play video with error ")
                true
            }
            start()
        }
    }

}