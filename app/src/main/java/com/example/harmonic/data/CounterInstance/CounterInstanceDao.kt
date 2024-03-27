package com.example.harmonic.data.CounterInstance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.harmonic.data.CounterInstance.LocalCounterInstance
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterInstanceDao {

    @Query("SELECT * FROM CounterInstance")
    fun observeAll(): Flow<List<LocalCounterInstance>>

    @Query("SELECT * FROM CounterInstance WHERE active = 1")
    fun observeActive(): Flow<List<LocalCounterInstance>>

    @Query("SELECT * FROM CounterInstance WHERE id = :id")
    suspend fun getInstance(id: Int): LocalCounterInstance?

    @Query("SELECT * FROM CounterInstance WHERE id = :id")
    fun observeInstance(id: Int): Flow<LocalCounterInstance>

    @Query("SELECT * FROM CounterInstance WHERE jobId = :jobId")
    fun observeInstancesForJob(jobId: Int): Flow<List<LocalCounterInstance>>

    @Insert
    suspend fun insert(localCounterInstance: LocalCounterInstance): Long

    @Upsert
    suspend fun upsert(localCounterInstance: LocalCounterInstance)

//    @Query("UPDATE CounterInstance SET count = :count WHERE id = :id")
//    suspend fun updateCountInstance(id: Int, count: Int)

}