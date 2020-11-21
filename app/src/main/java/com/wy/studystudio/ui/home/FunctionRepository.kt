package com.wy.studystudio.ui.home

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 *
 * @author  cantalou
 * @date    2020年11月14日 19:08
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
class FunctionRepository {
    suspend fun getFunctions(): List<Function> = withContext(Dispatchers.IO) {
        val list = mutableListOf<Function>()
        list.add(Function("com.wy.studystudio.ui.strategy.fragment.StrategyFragment", "", "复习策略"))
        list.add(Function("com.wy.studystudio.ui.strategy.fragment.StrategyFragment", "", "复习策略"))
        list.add(Function("com.wy.studystudio.ui.strategy.fragment.StrategyFragment", "", "复习策略"))
        list.add(Function("com.wy.studystudio.ui.strategy.fragment.StrategyFragment", "", "复习策略"))
        return@withContext list
    }
}