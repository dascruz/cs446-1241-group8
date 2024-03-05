package com.example.harmonic.data.TimerJob

import com.example.harmonic.models.jobs.TimerJobModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    suspend fun createLocal(tj: TimerJobModel) {
        return localDataSource.upsert(tj.toLocal())
    }
}