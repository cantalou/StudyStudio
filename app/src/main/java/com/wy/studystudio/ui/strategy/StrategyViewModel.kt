package com.wy.studystudio.ui.strategy

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.wy.studystudio.ui.strategy.model.Strategy

class StrategyViewModel(application: Application) : AndroidViewModel(application) {

    val data: MutableList<Strategy> = mutableListOf()

    suspend fun getAllStrategy(): MutableList<Strategy> {
        return data
    }

    suspend fun saveAllStrategy(data: MutableList<Strategy>) {
        data.clear()
        data.addAll(data)
    }
}