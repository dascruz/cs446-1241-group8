package com.example.harmonic.data.RoutineJob

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineJobDao {
    @Query("SELECT * FROM RoutineJob")
    fun observeAll(): Flow<List<LocalRoutineJob>>

    @Query("SELECT * FROM RoutineJob WHERE id = :id")
    fun observeById(id: Int): Flow<LocalRoutineJob>

    @Query("SELECT * FROM RoutineJob WHERE id = :id")
    suspend fun getById(id: Int): LocalRoutineJob?

    @Query("UPDATE RoutineJob SET name = :name WHERE id = :id")
    suspend fun updateRoutineJob(id: Int, name: String)


    @Upsert
    suspend fun upsert(localRoutineJob: LocalRoutineJob)
}