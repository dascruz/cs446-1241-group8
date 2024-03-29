package com.example.harmonic.data.TimerInstance

import com.example.harmonic.models.instances.TimerInstanceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.time.Instant
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

    fun observeInstance(id: Int): Flow<TimerInstanceModel> {
        return localDataSource.observeInstance(id).transform { it.toExternal() }
    }

    suspend fun getInstance(id: Int): TimerInstanceModel? {
        return localDataSource.getInstance(id)?.toExternal()
    }

    fun observeInstancesForJob(jobId: Int) : Flow<List<TimerInstanceModel>> {
        return localDataSource.observeInstancesForJob(jobId).map {
            ti -> ti.toExternal()
        }
    }

    fun observeShareableInstancesForJob(jobId: Int) : Flow<List<TimerInstanceModel>> {
        return localDataSource.observeShareableInstancesForJob(jobId).map {
                ti -> ti.toExternal()
        }
    }

    fun observeSharedInstancesForJob(jobId: Int) : Flow<List<TimerInstanceModel>> {
        return localDataSource.observeSharedInstancesForJob(jobId).map {
                ti -> ti.toExternal()
        }
    }

    fun observeInternalInstancesForJob(jobId: Int) : Flow<List<TimerInstanceModel>> {
        return localDataSource.observeInternalInstancesForJob(jobId).map {
                ti -> ti.toExternal()
        }
    }

    suspend fun insertLocal(ti: TimerInstanceModel): Int {
        val out = localDataSource.insert(ti.toLocal())
        println(out)
        return out.toInt()
    }

    suspend fun insertAll(lti: List<TimerInstanceModel>) {
        localDataSource.insertAll(lti.toLocal())
    }

    suspend fun upsertLocal(ti: TimerInstanceModel ) {
        localDataSource.upsert(ti.toLocal())
    }

    suspend fun updateActiveInstance(id: Int, active: Boolean, newSegment: Instant, newSegmentName: String) {
        localDataSource.updateActiveInstance(id, active, newSegment.toString(), newSegmentName)
    }

    suspend fun updateStartInstance(id: Int, startDateTime: Instant) {
        println("id: $id, sdt: $startDateTime")
        localDataSource.updateStartInstance(id, startDateTime.toString())
    }
}