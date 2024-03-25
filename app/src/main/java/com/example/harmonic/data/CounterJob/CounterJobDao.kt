package com.example.harmonic.data.CounterJob

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterJobDao {
    @Query("SELECT * FROM CounterJob")
    fun observeAll(): Flow<List<LocalCounterJob>>

    @Query("SELECT * FROM CounterJob WHERE id = :id")
    fun observeById(id: Int): Flow<LocalCounterJob>

    @Query("SELECT * FROM CounterJob WHERE id = :id")
    suspend fun getById(id: Int): LocalCounterJob?

    @Query("UPDATE CounterJob SET name = :name WHERE id = :id")
    suspend fun updateCounterJob(id: Int, name: String)

    @Upsert
    suspend fun upsert(localCounterJob: LocalCounterJob)
}