package com.wy.studystudio.extension

import android.util.Log

/**
 *
 *
 * @author  cantalou
 * @date    2020年11月15日 11:02
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
fun Any.v(msg: String, vararg args: Any?) {
    log(msg, *args) {
        Log.v(this.javaClass.simpleName, it)
    }
}

fun Any.d(msg: String, vararg args: Any?) {
    log(msg, *args) {
        Log.d(this.javaClass.simpleName, it)
    }
}

fun Any.i(msg: String, vararg args: Any?) {
    log(msg, *args) {
        Log.i(this.javaClass.simpleName, it)
    }
}

fun Any.w(msg: String, vararg args: Any?) {
    log(msg, *args) {
        Log.v(this.javaClass.simpleName, it)
    }
}

fun Any.e(msg: String, vararg args: Any?) {
    log(msg, *args) {
        Log.e(this.javaClass.simpleName, it)
    }
}

inline fun log(msg: String, vararg args: Any?, block: (msg: String) -> Unit) {
    val msgBuilder = StringBuffer(msg)
    if (args.isNotEmpty()) {
        var start = 0
        args.forEach {
            start = msg.indexOf("{}", start)
            if (start == -1) {
                return@forEach
            }
            val argStr = it?.toString() ?: ""
            msgBuilder.replaceRange(start, start + 1, argStr)
            start += argStr.length
        }
    }
    block(msgBuilder.toString())
}