package com.example.harmonic.data.CounterJob

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonic.models.jobs.CounterJobModel

@Entity(
    tableName = "CounterJob"
)
data class LocalCounterJob (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String
)

fun LocalCounterJob.toExternal() = CounterJobModel (
    id = id,
    name = name
)

fun List<LocalCounterJob>.toExternal() = map(LocalCounterJob::toExternal)

fun CounterJobModel.toLocal() = LocalCounterJob (
    id = id ?: 0,
    name = name
)

fun List<CounterJobModel>.toLocal() = map(CounterJobModel::toLocal)