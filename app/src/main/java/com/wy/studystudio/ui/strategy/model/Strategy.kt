package com.wy.studystudio.ui.strategy.model

import com.wy.studystudio.ui.strategy.model.Phase

/**
 *
 * @author  cantalou
 * @date    2020年11月15日 19:36
 *
 * Copyright (c) 2020年, WY CO.ltd. All Rights Reserved.
 */
data class Strategy(val index: Int, val phases: MutableList<Phase>) {
}