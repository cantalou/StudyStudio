package com.wy.studystudio.ui.me

import androidx.lifecycle.ViewModel

class MeViewModel : ViewModel() {

    private val functionRepository = FunctionRepository()

    suspend fun getFunctions(): List<Function> {
        return functionRepository.getFunctions()
    }
}