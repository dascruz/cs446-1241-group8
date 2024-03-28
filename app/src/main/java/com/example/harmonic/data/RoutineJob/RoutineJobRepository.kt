package com.example.harmonic.data.RoutineJob

import com.example.harmonic.models.jobs.RoutineJobModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoutineJobRepository @Inject constructor(
    private val localDataSource: RoutineJobDao,
) {
    fun observeAll(): Flow<List<RoutineJobModel>> {
        return localDataSource.observeAll().map {
                tj -> tj.toExternal()
        }
    }

    fun observeById(id: Int): Flow<RoutineJobModel> {
        return localDataSource.observeById(id).transform { it.toExternal() }
    }

    suspend fun getById(id: Int): RoutineJobModel? {
        return localDataSource.getById(id)?.toExternal()
    }

    suspend fun updateRoutineJob(id: Int, name: String) {
        localDataSource.updateRoutineJob(id, name)

    }

    suspend fun createLocal(tj: RoutineJobModel) {
        return localDataSource.upsert(tj.toLocal())
    }
}