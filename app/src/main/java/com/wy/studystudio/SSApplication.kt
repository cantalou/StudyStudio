package com.wy.studystudio

import android.app.Application
import android.content.Context

/**
 *
 *
 * @author  LinZhiWei
 * @date    2020/11/20
 *
 * Copyright (c) 2020年, 4399 Network CO.ltd. All Rights Reserved.
 */
class SSApplication : Application() {

    companion object {
        lateinit var INSTANCE: Application
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        INSTANCE = this
    }
}