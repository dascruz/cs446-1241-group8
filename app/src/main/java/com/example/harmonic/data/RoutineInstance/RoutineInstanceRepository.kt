package com.example.harmonic.data.RoutineInstance

import com.example.harmonic.models.instances.RoutineInstanceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoutineInstanceRepository @Inject constructor(
    private val localDataSource: RoutineInstanceDao,
) {
    fun observeAll(): Flow<List<RoutineInstanceModel>> {
        return localDataSource.observeAll().map {
                ti -> ti.toExternal()
        }
    }

    fun observeActive(): Flow<List<RoutineInstanceModel>> {
        return localDataSource.observeActive().map {
                ti -> ti.toExternal()
        }
    }

    fun observeInstance(id: Int): Flow<RoutineInstanceModel> {
        return localDataSource.observeInstance(id).transform { it.toExternal() }
    }

    suspend fun getInstance(id: Int): RoutineInstanceModel? {
        return localDataSource.getInstance(id)?.toExternal()
    }

    fun observeInstancesForJob(jobId: Int) : Flow<List<RoutineInstanceModel>> {
        return localDataSource.observeInstancesForJob(jobId).map {
                ti -> ti.toExternal()
        }
    }

    suspend fun insertLocal(ti: RoutineInstanceModel): Int {
        val out = localDataSource.insert(ti.toLocal())
        println(out)
        return out.toInt()
    }

    suspend fun upsertLocal(ti: RoutineInstanceModel) {
        localDataSource.upsert(ti.toLocal())
    }

/*    suspend fun updateActiveInstance(id: Int, active: Boolean, newSegment: Instant, newSegmentName: String) {
        localDataSource.updateActiveInstance(id, active, newSegment.toString(), newSegmentName)
    }*/

    suspend fun updateStartInstance(id: Int, startDateTime: Instant) {
        println("id: $id, sdt: $startDateTime")
        localDataSource.updateStartInstance(id, startDateTime.toString())
    }

    suspend fun updateEndInstance(id: Int, endDateTime: Instant) {
        println("id: $id, sdt: $endDateTime")
        localDataSource.updateEndInstance(id, endDateTime.toString())
    }

    suspend fun makeNotActive(id: Int) {
        localDataSource.makeNotActive(id, "false")
    }
}