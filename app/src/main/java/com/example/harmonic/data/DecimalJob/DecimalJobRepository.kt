package com.example.harmonic.data.DecimalJob

import com.example.harmonic.models.jobs.DecimalJobModel
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

@Singleton
class DecimalJobRepository
@Inject
constructor(
        private val localDataSource: DecimalJobDao,
) {
    fun observeAll(): Flow<List<DecimalJobModel>> {
        return localDataSource.observeAll().map { dj -> dj.toExternal() }
    }

    fun observeById(id: Int): Flow<DecimalJobModel> {
        return localDataSource.observeById(id).transform { it.toExternal() }
    }

    suspend fun getById(id: Int): DecimalJobModel? {
        return localDataSource.getById(id)?.toExternal()
    }

    suspend fun updateDecimalJob(id: Int, name: String) {
        localDataSource.updateDecimalJob(id, name)
    }

    suspend fun createLocal(dj: DecimalJobModel) {
        return localDataSource.upsert(dj.toLocal())
    }
}
