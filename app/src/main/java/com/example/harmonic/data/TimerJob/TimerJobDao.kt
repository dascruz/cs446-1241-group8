package com.example.harmonic.data.TimerJob

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TimerJobDao {
    @Query("SELECT * FROM TimerJob")
    fun observeAll(): Flow<List<LocalTimerJob>>

    @Query("UPDATE TimerJob SET name = :name WHERE id = :id")
    suspend fun updateTimerJob(id: UUID, name: String)

    @Upsert
    suspend fun upsert(localTimerJob: LocalTimerJob)
}