package com.wy.studystudio.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wy.studystudio.ui.common.activity.StandardActivity
import com.wy.studystudio.ui.common.vm.GlobalViewModelStore

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 18:54
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */

fun Context.startFragment(fragmentName: String, requestCode: Int = 0, args: Bundle = Bundle()) {
    val intent = Intent()
    intent.setClass(this, StandardActivity::class.java)
    intent.putExtras(args)
    intent.putExtra(StandardActivity.PENDING_FRAGMENT_CLASS_NAME, fragmentName)
    if (this is Activity) {
        startActivityForResult(intent, requestCode)
    } else {
        startActivity(intent)
    }
}

fun Context.startFragment(fragmentClass: Class<*>, requestCode: Int = 0, args: Bundle = Bundle()) {
    startFragment(fragmentClass.name, requestCode, args)
}

fun Activity.startFragment(fragmentName: String, requestCode: Int = 0, args: Bundle = Bundle()) {
    (this as Context).startFragment(fragmentName, requestCode, args)
}

fun Activity.startFragment(fragmentClass: Class<*>, requestCode: Int = 0, args: Bundle = Bundle()) {
    (this as Context).startFragment(fragmentClass, requestCode, args)
}

fun Fragment.startFragment(fragmentName: String, requestCode: Int = 0, args: Bundle = Bundle()) {
    (this.activity as Context).startFragment(fragmentName, requestCode, args)
}

fun Fragment.startFragment(fragmentClass: Class<*>, requestCode: Int = 0, args: Bundle = Bundle()) {
    (this.activity as Context).startFragment(fragmentClass, requestCode, args)
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun <T : ViewModel> Fragment.fvm(modelClass: Class<T>): T {
    return ViewModelProvider(this).get(modelClass)
}

fun <T : ViewModel> Fragment.avm(modelClass: Class<T>): T {
    return ViewModelProvider(requireActivity()).get(modelClass)
}

fun <T : ViewModel> AppCompatActivity.avm(modelClass: Class<T>): T {
    return ViewModelProvider(this).get(modelClass)
}

fun <T : ViewModel> Any.gvm(modelClass: Class<T>): T {
    return ViewModelProvider(GlobalViewModelStore).get(modelClass)
}