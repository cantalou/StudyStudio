package com.wy.studystudio.ui.strategy.vm

import android.app.Application
import com.wy.studystudio.ui.common.vm.BaseViewModel
import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData
import com.wy.studystudio.ui.strategy.model.Phase
import com.wy.studystudio.ui.strategy.model.Strategy


class StrategyViewModel(app: Application) : BaseViewModel<Strategy>(app, StrategyRepository(app)) {

    private var newStrategy: Strategy? = null

    override fun getAll(): MutableList<Strategy> {
        val result = super.getAll()
        if (result.isEmpty()) {
            add(crateDefault())
        }
        return super.getAll()
    }

    private fun crateDefault(): Strategy {
        val strategy = Strategy(1L, "艾宾浩斯默认策略")
        strategy.phases.apply {
            add(Phase(1, Phase.MINUTE_INTERVAL * 5, 1))
            add(Phase(2, Phase.MINUTE_INTERVAL * 30, 1))
            add(Phase(3, Phase.HOUR_INTERVAL * 12, 1))
            add(Phase(4, Phase.DAY_INTERVAL * 1, 1))
            add(Phase(5, Phase.DAY_INTERVAL * 2, 1))
            add(Phase(6, Phase.DAY_INTERVAL * 4, 1))
            add(Phase(7, Phase.DAY_INTERVAL * 7, 1))
            add(Phase(8, Phase.DAY_INTERVAL * 14, 1))
        }
        return strategy
    }

    fun addOrUpdate(phase: Phase) {
        var strategy = dataCached.find { it.id == phase.strategyId } ?: newStrategy!!
        if (phase.id == 0L) {
            val size = strategy.phases.size
            phase.id = if (size > 0) strategy.phases[size - 1].id + 1 else 1L
            strategy.phases.add(phase)
        } else {
            strategy.phases.find { it.id == phase.id }!!.interval = phase.interval
        }
    }

    fun get(id: Long): Strategy {
        if (id == newStrategy?.id) {
            return newStrategy!!
        }
        return dataCached.find { it.id == id }!!
    }

    fun createNewStrategy(): Strategy {
        val strategyId = if (dataCached.size > 0) dataCached[dataCached.size - 1].id + 1 else 1L
        newStrategy = Strategy(strategyId, "", MutableListWithLiveData())
        return newStrategy!!
    }

}