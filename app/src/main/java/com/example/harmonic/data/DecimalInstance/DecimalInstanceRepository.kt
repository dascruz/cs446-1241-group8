package com.example.harmonic.data.DecimalInstance

import com.example.harmonic.models.instances.DecimalInstanceModel
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

@Singleton
class DecimalInstanceRepository
@Inject
constructor(
        private val localDataSource: DecimalInstanceDao,
) {
    fun observeAll(): Flow<List<DecimalInstanceModel>> {
        return localDataSource.observeAll().map { di -> di.toExternal() }
    }

    fun observeActive(): Flow<List<DecimalInstanceModel>> {
        return localDataSource.observeActive().map { di -> di.toExternal() }
    }

    fun observeInstance(id: Int): Flow<DecimalInstanceModel> {
        return localDataSource.observeInstance(id).transform { it.toExternal() }
    }

    suspend fun getInstance(id: Int): DecimalInstanceModel? {
        return localDataSource.getInstance(id)?.toExternal()
    }

    fun observeInstancesForJob(jobId: Int): Flow<List<DecimalInstanceModel>> {
        return localDataSource.observeInstancesForJob(jobId).map { di -> di.toExternal() }
    }

    suspend fun insertLocal(di: DecimalInstanceModel): Int {
        val out = localDataSource.insert(di.toLocal())
        println(out)
        return out.toInt()
    }

    suspend fun upsertLocal(di: DecimalInstanceModel) {
        localDataSource.upsert(di.toLocal())
    }

    suspend fun updateActiveInstance(id: Int, active: Boolean, newValue: BigDecimal) {
        localDataSource.updateActiveInstance(id, active, newValue.toString())
    }

    suspend fun resetInstance(id: Int) {
        localDataSource.resetInstance(id)
    }
}
