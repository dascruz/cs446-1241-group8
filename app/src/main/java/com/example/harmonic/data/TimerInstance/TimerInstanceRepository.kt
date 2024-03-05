package com.example.harmonic.data.TimerInstance

import com.example.harmonic.models.instances.TimerInstanceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimerInstanceRepository @Inject constructor(
    private val localDataSource: TimerInstanceDao,
) {
    fun observeAll(): Flow<List<TimerInstanceModel>> {
        return localDataSource.observeAll().map {
            ti -> ti.toExternal()
        }
    }

    fun observeActive(): Flow<List<TimerInstanceModel>> {
        return localDataSource.observeActive().map {
            ti -> ti.toExternal()
        }
    }

    fun observeInstancesForJob(jobId: UUID) : Flow<List<TimerInstanceModel>> {
        return localDataSource.observeInstancesForJob(jobId).map {
            ti -> ti.toExternal()
        }
    }

    suspend fun createLocal(ti: TimerInstanceModel ) {
        localDataSource.upsert(ti.toLocal())
    }
}