package com.example.harmonic.data.DecimalInstance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import java.math.BigDecimal
import kotlinx.coroutines.flow.Flow

@Dao
interface DecimalInstanceDao {

    @Query("SELECT * FROM DecimalInstance") fun observeAll(): Flow<List<DecimalInstance>>

    @Query("SELECT * FROM DecimalInstance WHERE active = 1")
    fun observeActive(): Flow<List<DecimalInstance>>

    @Query("SELECT * FROM DecimalInstance WHERE id = :id")
    suspend fun getInstance(id: Int): DecimalInstance?

    @Query("SELECT * FROM DecimalInstance WHERE id = :id")
    fun observeInstance(id: Int): Flow<DecimalInstance>

    @Query("SELECT * FROM DecimalInstance WHERE jobId = :jobId")
    fun observeInstancesForJob(jobId: Int): Flow<List<DecimalInstance>>

    @Insert suspend fun insert(decimalInstance: DecimalInstance): Long

    @Upsert suspend fun upsert(decimalInstance: DecimalInstance)

    @Query("UPDATE DecimalInstance SET startDateTime = :startDateTime WHERE id = :id")
    suspend fun updateStartInstance(id: Int, startDateTime: String)

    @Query("UPDATE DecimalInstance SET active = :active WHERE id = :id")
    suspend fun updateActiveInstance(id: Int, active: Boolean)

    @Query("UPDATE DecimalInstance SET value = :value WHERE id = :id")
    suspend fun updateValue(id: Int, value: BigDecimal)
}
