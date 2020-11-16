package com.wy.studystudio.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.wy.studystudio.ui.common.StandardActivity
import java.util.*

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 18:54
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */

fun Context.startFragment(fragmentName: String, requestCode: Int = 0, args: Map<String, Parcelable> = Collections.emptyMap()) {
    val intent = Intent()
    intent.setClass(this, StandardActivity::class.java)
    args.forEach { (k, v) ->
        intent.putExtra(k, v)
    }
    intent.putExtra(StandardActivity.PENDING_FRAGMENT_CLASS_NAME, fragmentName)
    if (this is Activity) {
        startActivityForResult(intent, requestCode)
    } else {
        startActivity(intent)
    }
}

fun Context.startFragment(fragmentClass: Class<*>, requestCode: Int = 0, args: Map<String, Parcelable> = Collections.emptyMap()) {
    startFragment(fragmentClass.name, requestCode, args)
}

fun Activity.startFragment(fragmentName: String, requestCode: Int = 0, args: Map<String, Parcelable> = Collections.emptyMap()) {
    (this as Context).startFragment(fragmentName, requestCode, args)
}

fun Activity.startFragment(fragmentClass: Class<*>, requestCode: Int = 0, args: Map<String, Parcelable> = Collections.emptyMap()) {
    (this as Context).startFragment(fragmentClass, requestCode, args)
}

fun Fragment.startFragment(fragmentName: String, requestCode: Int = 0, args: Map<String, Parcelable> = Collections.emptyMap()) {
    (this.activity as Context).startFragment(fragmentName, requestCode, args)
}

fun Fragment.startFragment(fragmentClass: Class<*>, requestCode: Int = 0, args: Map<String, Parcelable> = Collections.emptyMap()) {
    (this.activity  as Context).startFragment(fragmentClass, requestCode, args)
}