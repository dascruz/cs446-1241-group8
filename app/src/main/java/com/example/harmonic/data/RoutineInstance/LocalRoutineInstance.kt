package com.example.harmonic.data.RoutineInstance

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonic.models.instances.RoutineInstanceModel
import java.time.Instant

@Entity(
    tableName = "RoutineInstance"
)
data class LocalRoutineInstance(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val creationDateTime: String,
    var active: Boolean,
    var internal: Boolean,
    var friendId: Int?,
    var jobId: Int?,
    var jobName: String?,
    var jobInstanceNum: Int?,
    var startDateTime: String?,
    var endDateTime: String?,
)

fun LocalRoutineInstance.toExternal() : RoutineInstanceModel {
    val routineInstance = RoutineInstanceModel(
        id = id,
        active = active,
        creationDateTime = Instant.parse(creationDateTime),
        internal = internal,
        initStartDateTime = if (startDateTime != null) startDateTime!!.toInt() else null,
        initEndDateTime = if (endDateTime != null) endDateTime!!.toInt() else null,
        initFriendId = friendId,
        initJobId = jobId,
        initJobName = jobName,
        initJobInstanceNum = jobInstanceNum
    )

    return routineInstance
}

fun List<LocalRoutineInstance>.toExternal() = map(LocalRoutineInstance::toExternal)

fun RoutineInstanceModel.toLocal(): LocalRoutineInstance {

    return LocalRoutineInstance(
        id = id ?: 0,
        creationDateTime = creationDateTime.toString(),
        active = active,
        internal = internal,
        friendId = friendId,
        jobId = jobId,
        jobName = jobName,
        jobInstanceNum = jobInstanceNum,
        startDateTime = startDateTime?.toString(),
        endDateTime = endDateTime?.toString()
    )
}

fun List<RoutineInstanceModel>.toLocal() = map(RoutineInstanceModel::toLocal)