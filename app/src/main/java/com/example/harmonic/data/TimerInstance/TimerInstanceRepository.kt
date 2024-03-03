package com.example.harmonic.data.TimerInstance

import com.example.harmonic.di.ApplicationScope
import com.example.harmonic.di.DefaultDispatcher
import com.example.harmonic.models.instances.TimerInstanceModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimerInstanceRepository @Inject constructor(
    private val localDataSource: TimerInstanceDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineDispatcher
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

    suspend fun createLocal(ti: TimerInstanceModel ) {
        localDataSource.upsert(ti.toLocal())
    }
}