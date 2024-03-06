package com.example.harmonic.data.TimerJob

import com.example.harmonic.models.jobs.TimerJobModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.util.UUID
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

    fun observeById(id: UUID): Flow<TimerJobModel> {
        return localDataSource.observeById(id).transform { it.toExternal() }
    }

    suspend fun createLocal(tj: TimerJobModel) {
        return localDataSource.upsert(tj.toLocal())
    }
}