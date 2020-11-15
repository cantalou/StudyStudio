package com.wy.studystudio.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.wy.studystudio.ui.common.StandardActivity

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 18:54
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */

fun Context.startFragment(fragmentName: String, requestCode: Int = 0) {
    val intent = Intent()
    intent.setClass(this, StandardActivity::class.java)
    intent.putExtra(StandardActivity.PENDING_FRAGMENT_CLASS_NAME, fragmentName)
    if (this is Activity) {
        startActivityForResult(intent, requestCode)
    } else {
        startActivity(intent)
    }
}

fun Context.startFragment(fragmentClass: Class<*>, requestCode: Int = 0) {
    startFragment(fragmentClass.name, requestCode)
}

fun Activity.startFragment(fragmentName: String, requestCode: Int = 0) {
    val intent = Intent()
    intent.setClass(this, StandardActivity::class.java)
    intent.putExtra(StandardActivity.PENDING_FRAGMENT_CLASS_NAME, fragmentName)
    startActivityForResult(intent, requestCode)
}

fun Activity.startFragment(fragmentClass: Class<*>, requestCode: Int = 0) {
    startFragment(fragmentClass.name, requestCode)
}

fun Fragment.startFragment(fragmentName: String, requestCode: Int = 0) {
    val intent = Intent()
    intent.setClass(context!!, StandardActivity::class.java)
    intent.putExtra(StandardActivity.PENDING_FRAGMENT_CLASS_NAME, fragmentName)
    startActivityForResult(intent, requestCode)
}

fun Fragment.startFragment(fragmentClass: Class<*>, requestCode: Int = 0) {
    startFragment(fragmentClass.name, requestCode)
}