package com.wy.studystudio.ui.common.vm

import androidx.lifecycle.*
import com.wy.studystudio.SSApplication

/**
 *
 *
 * @author  cantalou
 * @date    2020/11/20
 *
 */
object GlobalViewModelStore : ViewModelStoreOwner, HasDefaultViewModelProviderFactory {

    private val store: ViewModelStore by lazy {
        ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return store
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(SSApplication.INSTANCE)
    }

}