package com.example.harmonic.data.TimerJob

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerJobDao {
    @Query("SELECT * FROM TimerJob")
    fun observeAll(): Flow<List<LocalTimerJob>>

    @Query("SELECT * FROM TimerJob WHERE id = :id")
    fun observeById(id: Int): Flow<LocalTimerJob>

    @Query("SELECT * FROM TimerJob WHERE id = :id")
    suspend fun getById(id: Int): LocalTimerJob?

    @Query("UPDATE TimerJob SET name = :name WHERE id = :id")
    suspend fun updateTimerJob(id: Int, name: String)


    @Upsert
    suspend fun upsert(localTimerJob: LocalTimerJob)
}