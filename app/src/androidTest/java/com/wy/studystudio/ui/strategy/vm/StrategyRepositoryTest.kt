package com.wy.studystudio.ui.strategy.vm

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wy.studystudio.ui.me.strategy.model.Phase
import com.wy.studystudio.ui.me.strategy.model.Strategy
import com.wy.studystudio.ui.me.strategy.vm.StrategyRepository
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

/**
 * @author cantalou
 * @date 2020/11/17
 *
 *
 */
@RunWith(AndroidJUnit4::class)
class StrategyRepositoryTest {

    private lateinit var repository: StrategyRepository

    @Before
    fun setUp() {
        repository = StrategyRepository(ApplicationProvider.getApplicationContext())
        repository.saveAll(listOf())
    }

    @After
    fun tearDown() {
        repository.saveAll(listOf())
    }

    @Test
    fun getAll() {
    }

    @Test
    fun add() {
        assertEquals(0, repository.getAll().size)
        repository.add(Strategy(2L, ""))
        assertEquals(1, repository.getAll().size)
        assertEquals(2L, repository.getAll()[0].id)
    }

    @Test
    fun update() {
        saveAll()

        repository.update(Strategy(1L, "1-1"))
        repository.update(Strategy(2L, "2-2"))
        repository.getAll().apply {
            assert("1-1" == this[0].name)
            assert("2-2" == this[1].name)
        }
    }

    @Test
    fun delete() {
        saveAll()
        repository.delete(Strategy(2L, ""))
        repository.getAll().apply {
            assert(this[0].id == 1L)
            assert(this[1].id == 3L)
        }
    }

    @Test
    fun saveAll() {
        val data = mutableListOf<Strategy>()
        for (strategyId in 1 until 6) {
            val strategy = Strategy(strategyId.toLong(), strategyId.toString())
            for (phaseId in 1 until 6) {
                strategy.phases.add(Phase(phaseId.toLong(), phaseId.toLong(), strategy.id))
            }
            data.add(strategy)
        }
        repository.saveAll(data)

        val newData = repository.getAll()
        assertEquals(5, newData.size)
        assertEquals(newData, data)
    }

}