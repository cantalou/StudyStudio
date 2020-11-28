package com.wy.studystudio.ui.me

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val functionRepository = FunctionRepository()

    suspend fun getFunctions(): List<Function> {
        return functionRepository.getFunctions()
    }
}