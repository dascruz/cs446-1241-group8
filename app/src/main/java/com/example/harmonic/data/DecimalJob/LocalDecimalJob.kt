package com.example.harmonic.data.DecimalJob

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonic.models.jobs.DecimalJobModel

@Entity(tableName = "DecimalJob")
data class LocalDecimalJob(
        @PrimaryKey(autoGenerate = true) val id: Int,
        var name: String,
        var numDecimals: Int,
)

fun LocalDecimalJob.toExternal() = DecimalJobModel(id = id, name = name, numDecimals = numDecimals)

fun List<LocalDecimalJob>.toExternal() = map(LocalDecimalJob::toExternal)

fun DecimalJobModel.toLocal() =
        LocalDecimalJob(id = id ?: 0, name = name, numDecimals = numDecimals)

fun List<DecimalJobModel>.toLocal() = map(DecimalJobModel::toLocal)
