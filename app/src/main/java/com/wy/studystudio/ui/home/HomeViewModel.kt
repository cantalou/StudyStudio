package com.wy.studystudio.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val functionRepository = FunctionRepository()

    suspend fun getFunctions(): List<Function> {
        return functionRepository.getFunctions()
    }
}