package com.example.harmonic.data.CounterInstance

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonic.models.instances.CounterInstanceModel
import java.time.Instant

@Entity(
    tableName = "CounterInstance"
)
data class LocalCounterInstance(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val creationDateTime: String,
    var active: Boolean,
    var internal: Boolean,
    var friendId: Int?,
    var jobId: Int?,
    var jobName: String?,
    var jobInstanceNum: Int?,
    var count: Int
)

fun LocalCounterInstance.toExternal() : CounterInstanceModel {
    val counterInstance = CounterInstanceModel(
        id = id,
        active = active,
        creationDateTime = Instant.parse(creationDateTime),
        internal = internal,
        initFriendId = friendId,
        initJobId = jobId,
        initJobName = jobName,
        initJobInstanceNum = jobInstanceNum,
        initialValue = count
    )

    return counterInstance
}

fun List<LocalCounterInstance>.toExternal() = map(LocalCounterInstance::toExternal)

fun CounterInstanceModel.toLocal(): LocalCounterInstance {

    return LocalCounterInstance(
        id = id ?: 0,
        creationDateTime = creationDateTime.toString(),
        active = active,
        internal = internal,
        friendId = friendId,
        jobId = jobId,
        jobName = jobName,
        jobInstanceNum = jobInstanceNum,
        count = count
    )
}

fun List<CounterInstanceModel>.toLocal() = map(CounterInstanceModel::toLocal)