package com.example.harmonic.data.TimerJob

import com.example.harmonic.models.jobs.TimerJobModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimerJobRepository @Inject constructor(
    private val localDataSource: TimerJobDao,
) {
    fun observeAll(): Flow<List<TimerJobModel>> {
        return localDataSource.observeAll().map {
            tj -> tj.toExternal()
        }
    }

    fun observeById(id: Int): Flow<TimerJobModel> {
        return localDataSource.observeById(id).transform { it.toExternal() }
    }

    suspend fun getById(id: Int): TimerJobModel? {
        return localDataSource.getById(id)?.toExternal()
    }

    suspend fun updateTimerJob(id: Int, name: String) {
        localDataSource.updateTimerJob(id, name)

    }

    suspend fun createLocal(tj: TimerJobModel) {
        return localDataSource.upsert(tj.toLocal())
    }
}