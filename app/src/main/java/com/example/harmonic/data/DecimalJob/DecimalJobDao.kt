package com.example.harmonic.data.DecimalJob

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DecimalJobDao {
    @Query("SELECT * FROM DecimalJob") fun observeAll(): Flow<List<LocalDecimalJob>>

    @Query("SELECT * FROM DecimalJob WHERE id = :id")
    fun observeById(id: Int): Flow<LocalDecimalJob>

    @Query("SELECT * FROM DecimalJob WHERE id = :id") suspend fun getById(id: Int): LocalDecimalJob?

    @Query("UPDATE DecimalJob SET name = :name WHERE id = :id")
    suspend fun updateDecimalJob(id: Int, name: String)

    @Upsert suspend fun upsert(localDecimalJob: LocalDecimalJob)

    @Query("UPDATE DecimalJob SET name = :name, numDecimals = :numDecimals WHERE id = :id")
    suspend fun updateDecimalJob(id: Int, name: String, numDecimals: Int)
}
