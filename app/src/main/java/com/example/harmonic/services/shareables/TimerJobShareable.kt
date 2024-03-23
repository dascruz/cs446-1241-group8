package com.example.harmonic.services.shareables

import com.example.harmonic.models.jobs.SegmentationTypes
import com.example.harmonic.models.jobs.TimerJobModel
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class TimerJobShareable (
    @DocumentId val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val segmentationType: String = "NONE",
    val segmentationValue: Int = 1,
)

fun TimerJobShareable.toExternal(name: String): TimerJobModel {
    var segmentationTypeEnum = SegmentationTypes.NONE
    if (this.segmentationType == "SET") {
        segmentationTypeEnum = SegmentationTypes.SET
    } else if (this.segmentationType == "CAP") {
        segmentationTypeEnum = SegmentationTypes.CAP
    }
    return TimerJobModel(
        name = name,
        segmentationType = segmentationTypeEnum,
        segmentationValue = this.segmentationValue
    )
}

fun TimerJobModel.toShareable() = TimerJobShareable (
    segmentationType = segmentationType.toString(),
    segmentationValue = segmentationValue
)

fun List<TimerJobModel>.toShareable() = map(TimerJobModel::toShareable)