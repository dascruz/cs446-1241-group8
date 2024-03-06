package com.example.harmonic.data.TimerInstance

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.harmonic.models.instances.TimerInstanceModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TimerInstanceDao {

    @Query("SELECT * FROM TimerInstance")
    fun observeAll(): Flow<List<LocalTimerInstance>>

    @Query("SELECT * FROM TimerInstance WHERE active = 1")
    fun observeActive(): Flow<List<LocalTimerInstance>>

    @Query("SELECT * FROM TimerInstance WHERE id = :id")
    fun observeInstance(id: UUID): Flow<TimerInstanceModel>

    @Query("SELECT * FROM TimerInstance WHERE jobId = :jobId")
    fun observeInstancesForJob(jobId: UUID) : Flow<List<LocalTimerInstance>>

    @Upsert
    suspend fun upsert(localTimerInstance: LocalTimerInstance)

    @Query("UPDATE TimerInstance SET startDateTime = :startDateTime WHERE id = :id")
    suspend fun updateStartInstance(id: UUID, startDateTime: String)

    @Query("UPDATE TimerInstance SET segments = segments || :segment, segmentNames = segmentNames || :segmentName, active = :active WHERE id = :id")
    suspend fun updateActiveInstance(id: UUID, active: Boolean, segment: String, segmentName: String)
}