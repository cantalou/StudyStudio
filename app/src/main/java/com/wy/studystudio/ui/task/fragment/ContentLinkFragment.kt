package com.wy.studystudio.ui.task.fragment

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wy.studystudio.R
import com.wy.studystudio.databinding.FragmentContentLinkBinding

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月21日 23:20
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class ContentLinkFragment(adapter: ShowContentAdapter) : ShowContentFragment<FragmentContentLinkBinding>(adapter) {

    override fun contentLayoutId(): Int {
        return R.layout.fragment_content_link
    }

    override fun initView(viewRoot: ViewGroup, inflater: LayoutInflater) {
        super.initView(viewRoot, inflater)
        cvdb.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                cvdb.webViewLoading.visibility = View.GONE
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                cvdb.webViewLoading.visibility = View.VISIBLE
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                cvdb.webViewLoading.visibility = View.GONE
            }
        }
    }
}