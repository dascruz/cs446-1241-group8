package com.example.harmonic.data.TimerInstance

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TimerInstanceDao {

    @Query("SELECT * FROM TimerInstance")
    fun observeAll(): Flow<List<LocalTimerInstance>>

    @Query("SELECT * FROM TimerInstance WHERE active = 1")
    fun observeActive(): Flow<List<LocalTimerInstance>>

    @Query("SELECT * FROM TimerInstance WHERE jobId = :jobId")
    fun observeInstancesForJob(jobId: UUID) : Flow<List<LocalTimerInstance>>

    @Upsert
    suspend fun upsert(localTimerInstance: LocalTimerInstance)
}