package com.example.harmonic.data.RoutineJob

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonic.models.jobs.RoutineJobModel

@Entity(
    tableName = "RoutineJob"
)
data class LocalRoutineJob (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
)

fun LocalRoutineJob.toExternal() = RoutineJobModel (
    id = id,
    name = name,
)

fun List<LocalRoutineJob>.toExternal() = map(LocalRoutineJob::toExternal)

fun RoutineJobModel.toLocal() = LocalRoutineJob (
    id = id ?: 0,
    name = name,
)

fun List<RoutineJobModel>.toLocal() = map(RoutineJobModel::toLocal)