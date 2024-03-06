package com.example.harmonic.data.TimerInstance

import com.example.harmonic.models.instances.TimerInstanceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.time.Instant
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

    fun observeInstance(id: UUID): Flow<TimerInstanceModel> {
        return localDataSource.observeInstance(id).transform { it.toExternal() }
    }

    fun observeInstancesForJob(jobId: UUID) : Flow<List<TimerInstanceModel>> {
        return localDataSource.observeInstancesForJob(jobId).map {
            ti -> ti.toExternal()
        }
    }

    suspend fun createLocal(ti: TimerInstanceModel ) {
        localDataSource.upsert(ti.toLocal())
    }

    suspend fun updateActiveInstance(id: UUID, active: Boolean, newSegment: Instant, newSegmentName: String) {
        localDataSource.updateActiveInstance(id, active, newSegment.toString(), newSegmentName)
    }

    suspend fun updateStartInstance(id: UUID, startDateTime: Instant) {
        localDataSource.updateStartInstance(id, startDateTime.toString())
    }
}