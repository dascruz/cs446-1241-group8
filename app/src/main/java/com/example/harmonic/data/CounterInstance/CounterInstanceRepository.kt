package com.example.harmonic.data.CounterInstance


import com.example.harmonic.models.instances.CounterInstanceModel
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounterInstanceRepository @Inject constructor(
    private val localDataSource: CounterInstanceDao,
) {
    fun observeAll(): Flow<List<CounterInstanceModel>> {
        return localDataSource.observeAll().map { ti ->
            ti.toExternal()
        }
    }

    fun observeActive(): Flow<List<CounterInstanceModel>> {
        return localDataSource.observeActive().map { ti ->
            ti.toExternal()
        }
    }

    fun observeInstance(id: Int): Flow<CounterInstanceModel> {
        return localDataSource.observeInstance(id).transform { it.toExternal() }
    }

    suspend fun getInstance(id: Int): CounterInstanceModel? {
        return localDataSource.getInstance(id)?.toExternal()
    }

    fun observeInstancesForJob(jobId: Int): Flow<List<CounterInstanceModel>> {
        return localDataSource.observeInstancesForJob(jobId).map { ti ->
            ti.toExternal()
        }
    }

    suspend fun insertLocal(ti: CounterInstanceModel): Int {
        val out = localDataSource.insert(ti.toLocal())
        println(out)
        return out.toInt()
    }

    suspend fun upsertLocal(ti: CounterInstanceModel) {
        localDataSource.upsert(ti.toLocal())
    }
}