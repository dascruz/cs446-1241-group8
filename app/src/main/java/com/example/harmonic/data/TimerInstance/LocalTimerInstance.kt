package com.example.harmonic.data.TimerInstance

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonic.models.instances.TimerInstanceModel
import java.time.Instant
import java.util.UUID

@Entity(
    tableName = "TimerInstance"
)
data class LocalTimerInstance(
    @PrimaryKey val id: UUID,
    val creationDateTime: String,
    var active: Boolean,
    var internal: Boolean,
    var friendId: UUID?,
    var jobId: UUID?,
    var jobName: String?,
    var jobInstanceNum: Int?,
    var startDateTime: String?,
    var segments: String,
    var segmentNames: String
)

fun LocalTimerInstance.toExternal() : TimerInstanceModel {
    val timerInstance = TimerInstanceModel(
        id = id,
        active = active,
        creationDateTime = Instant.parse(creationDateTime),
        internal = internal,
        initStartDateTime = if (startDateTime != null) Instant.parse(startDateTime) else null,
        initFriendId = friendId,
        initJobId = jobId,
        initJobName = jobName,
        initJobInstanceNum = jobInstanceNum
    )

    if (segments.isNotBlank() && segmentNames.isNotBlank()) {
        val instantsStrings = segments.split(",")
        val namesStrings = segments.split(",")
        val segmentsInstants = instantsStrings.map { s -> Instant.parse(s.trim()) }
        timerInstance.addSegments(segmentsInstants, namesStrings)
    }
    return timerInstance
}

fun List<LocalTimerInstance>.toExternal() = map(LocalTimerInstance::toExternal)

fun TimerInstanceModel.toLocal(): LocalTimerInstance {
    val segmentsString = getSegments().joinToString(",")
    val namesString = getSegmentNames().joinToString(",")

    return LocalTimerInstance(
        id = id,
        creationDateTime = creationDateTime.toString(),
        active = active,
        internal = internal,
        friendId = friendId,
        jobId = jobId,
        jobName = jobName,
        jobInstanceNum = jobInstanceNum,
        startDateTime = startDateTime?.toString(),
        segments = segmentsString,
        segmentNames = namesString
    )
}

fun List<TimerInstanceModel>.toLocal() = map(TimerInstanceModel::toLocal)

