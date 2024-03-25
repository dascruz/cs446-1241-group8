package com.example.harmonic.data.CounterJob

import com.example.harmonic.models.jobs.CounterJobModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounterJobRepository @Inject constructor(
    private val localDataSource: CounterJobDao,
) {
    fun observeAll(): Flow<List<CounterJobModel>> {
        return localDataSource.observeAll().map { tj ->
            tj.toExternal()
        }
    }

    fun observeById(id: Int): Flow<CounterJobModel> {
        return localDataSource.observeById(id).transform { it.toExternal() }
    }

    suspend fun getById(id: Int): CounterJobModel? {
        return localDataSource.getById(id)?.toExternal()
    }

    suspend fun updateCounterJob(id: Int, name: String) {
        localDataSource.updateCounterJob(id, name)

    }
}