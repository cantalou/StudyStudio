package com.wy.studystudio

import android.app.Application
import android.content.Context
import com.tencent.bugly.Bugly

/**
 *
 * @author  cantalou
 * @date    2020/11/20
 *
 * Copyright (c) 2020年, WY Network CO.ltd. All Rights Reserved.
 */
class SSApplication : Application() {

    companion object {
        lateinit var INSTANCE: Application
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        Bugly.init(applicationContext, "6ec4eb4289", false);
    }
}