package com.example.harmonic.data.TimerJob

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonic.models.jobs.SegmentationTypes
import com.example.harmonic.models.jobs.TimerJobModel

@Entity(
    tableName = "TimerJob"
)
data class LocalTimerJob (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var segmentationType: SegmentationTypes,
    var segmentationValue: Int,
)

fun LocalTimerJob.toExternal() = TimerJobModel (
    id = id,
    name = name,
    segmentationType = segmentationType,
    segmentationValue = segmentationValue
)

fun List<LocalTimerJob>.toExternal() = map(LocalTimerJob::toExternal)

fun TimerJobModel.toLocal() = LocalTimerJob (
    id = id ?: 0,
    name = name,
    segmentationType = segmentationType,
    segmentationValue = segmentationValue
)

fun List<TimerJobModel>.toLocal() = map(TimerJobModel::toLocal)