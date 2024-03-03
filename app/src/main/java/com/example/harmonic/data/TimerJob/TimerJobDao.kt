package com.example.harmonic.data.TimerJob

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerJobDao {
    @Query("SELECT * FROM TimerJob")
    fun observeAll(): Flow<List<LocalTimerJob>>

    @Upsert
    suspend fun upsert(localTimerJob: LocalTimerJob)
}