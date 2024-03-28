package com.example.harmonic.services.shareables

import com.example.harmonic.models.instances.TimerInstanceModel
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.time.Instant
import java.util.Date

data class TimerInstanceShareable (
    @DocumentId val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val creationDateTime: String = "",
    val startDateTime: String = "",
    val segments: List<String> = listOf<String>(),
    val segmentNames: List<String> = listOf<String>()
)

fun TimerInstanceShareable.toExternal(jobId: Int, jobName: String): TimerInstanceModel {
    val timerInstance = TimerInstanceModel(
        active = false,
        creationDateTime = Instant.parse(this.creationDateTime),
        internal = false,
        initStartDateTime = Instant.parse(this.startDateTime),
        initJobId = jobId,
        initJobName = jobName,
    )
    val segmentsInstants = this.segments.map { s -> Instant.parse(s.trim()) }
    timerInstance.addSegments(segmentsInstants, this.segmentNames)
    return timerInstance
}

fun List<TimerInstanceShareable>.toExternal(jobId: Int, jobName: String): List<TimerInstanceModel> {
    return this.map { it.toExternal(jobId, jobName) }
}

fun TimerInstanceModel.toShareable(): TimerInstanceShareable {
    val segmentsStrings = getSegments().map { s -> s.toString() }
    return TimerInstanceShareable(
        creationDateTime = this.creationDateTime.toString(),
        startDateTime = this.startDateTime.toString(),
        segments = segmentsStrings,
        segmentNames = getSegmentNames()
    )
}

fun List<TimerInstanceModel>.toShareable() = map(TimerInstanceModel::toShareable)