package com.wy.studystudio.ui.strategy.vm

import android.content.Context
import androidx.core.content.edit
import com.wy.studystudio.ui.common.vm.BaseRepository
import com.wy.studystudio.ui.common.livedata.MutableListWithLiveData
import com.wy.studystudio.ui.strategy.model.Phase
import com.wy.studystudio.ui.strategy.model.Strategy
import org.json.JSONArray
import org.json.JSONObject

/**
 *
 *
 * @author  cantalou
 * @date    2020/11/17
 *
 */
class StrategyRepository(private val context: Context) : BaseRepository<Strategy> {

    override fun getAll(): MutableList<Strategy> {
        val strategyJson = context.getSharedPreferences("Strategy", Context.MODE_PRIVATE).getString("Strategy", "[]")
        val strategyJA = JSONArray(strategyJson)
        val data = mutableListOf<Strategy>()
        for (index in 0 until strategyJA.length()) {

            val strategyJ = strategyJA[index] as JSONObject

            val phases = MutableListWithLiveData<Phase>()
            val phaseJA = strategyJ.getJSONArray("phases")
            for (index in 0 until phaseJA.length()) {
                val phaseJ = phaseJA[index] as JSONObject
                val phase = Phase(phaseJ.getLong("id"), phaseJ.getLong("interval"), phaseJ.getLong("strategyId"))
                phases.add(phase)
            }

            val strategy = Strategy(strategyJ.getLong("id"), strategyJ.getString("name"),phases)
            data.add(strategy)
        }
        return data
    }

    override fun saveAll(data: List<Strategy>) {
        val strategyJA = JSONArray()
        data.forEachIndexed { index, strategy ->
            strategyJA.put(index, strategy2Json(strategy))
        }
        context.getSharedPreferences("Strategy", Context.MODE_PRIVATE).edit {
            putString("Strategy", strategyJA.toString())
        }
    }

    private fun strategy2Json(strategy: Strategy): JSONObject {
        val strategyJ = JSONObject()
        strategyJ.put("id", strategy.id)
        strategyJ.put("name", strategy.name)

        val phaseJA = JSONArray()
        strategy.phases.forEachIndexed { index, phase ->
            phaseJA.put(index, phase2Json(phase))
        }
        strategyJ.put("phases", phaseJA)
        return strategyJ
    }

    private fun phase2Json(phase: Phase): JSONObject {
        val phaseJ = JSONObject()
        phaseJ.put("id", phase.id)
        phaseJ.put("interval", phase.interval)
        phaseJ.put("strategyId", phase.strategyId)
        return phaseJ
    }
}