package com.example.harmonic.data.TimerJob

import com.example.harmonic.di.ApplicationScope
import com.example.harmonic.di.DefaultDispatcher
import com.example.harmonic.models.jobs.TimerJobModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimerJobRepository @Inject constructor(
    private val localDataSource: TimerJobDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineDispatcher
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