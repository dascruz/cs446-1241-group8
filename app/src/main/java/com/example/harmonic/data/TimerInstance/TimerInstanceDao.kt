package com.example.harmonic.data.TimerInstance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerInstanceDao {

    @Query("SELECT * FROM TimerInstance")
    fun observeAll(): Flow<List<LocalTimerInstance>>

    @Query("SELECT * FROM TimerInstance WHERE active = 1")
    fun observeActive(): Flow<List<LocalTimerInstance>>

    @Query("SELECT * FROM TIMERINSTANCE WHERE id = :id")
    suspend fun getInstance(id: Int): LocalTimerInstance?

    @Query("SELECT * FROM TimerInstance WHERE id = :id")
    fun observeInstance(id: Int): Flow<LocalTimerInstance>

    @Query("SELECT * FROM TimerInstance WHERE jobId = :jobId")
    fun observeInstancesForJob(jobId: Int) : Flow<List<LocalTimerInstance>>

    @Insert
    suspend fun insert(localTimerInstance: LocalTimerInstance): Long

    @Upsert
    suspend fun upsert(localTimerInstance: LocalTimerInstance)

    @Query("UPDATE TimerInstance SET startDateTime = :startDateTime WHERE id = :id")
    suspend fun updateStartInstance(id: Int, startDateTime: String)

    @Query("UPDATE TimerInstance SET segments = segments || ',' || :segment, segmentNames = segmentNames || ',' || :segmentName, active = :active WHERE id = :id")
    suspend fun updateActiveInstance(id: Int, active: Boolean, segment: String, segmentName: String)
}