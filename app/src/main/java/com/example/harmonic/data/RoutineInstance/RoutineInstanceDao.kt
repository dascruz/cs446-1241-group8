package com.example.harmonic.data.RoutineInstance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineInstanceDao {

    @Query("SELECT * FROM RoutineInstance")
    fun observeAll(): Flow<List<LocalRoutineInstance>>

    @Query("SELECT * FROM RoutineInstance WHERE active = 1")
    fun observeActive(): Flow<List<LocalRoutineInstance>>

    @Query("SELECT * FROM TIMERINSTANCE WHERE id = :id")
    suspend fun getInstance(id: Int): LocalRoutineInstance?

    @Query("SELECT * FROM RoutineInstance WHERE id = :id")
    fun observeInstance(id: Int): Flow<LocalRoutineInstance>

    @Query("SELECT * FROM RoutineInstance WHERE jobId = :jobId")
    fun observeInstancesForJob(jobId: Int) : Flow<List<LocalRoutineInstance>>

    @Insert
    suspend fun insert(localRoutineInstance: LocalRoutineInstance): Long

    @Upsert
    suspend fun upsert(localRoutineInstance: LocalRoutineInstance)

    @Query("UPDATE RoutineInstance SET startDateTime = :startDateTime WHERE id = :id")
    suspend fun updateStartInstance(id: Int, startDateTime: String)

    @Query("UPDATE RoutineInstance SET endDateTime = :endDateTime WHERE id = :id")
    suspend fun updateEndInstance(id: Int, endDateTime: String)
}