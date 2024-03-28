package com.example.harmonic.data.DecimalInstance

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonic.models.instances.DecimalInstanceModel
import java.math.BigDecimal

@Entity(tableName = "DecimalInstance")
data class LocalDecimalInstance(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val creationDateTime: String,
        var active: Boolean,
        var internal: Boolean,
        var friendId: Int?,
        var jobId: Int?,
        var jobName: String?,
        var jobInstanceNum: Int?,
        var startDateTime: String?,
        var value: BigDecimal
)

fun LocalDecimalInstance.toExternal(): DecimalInstanceModel {
    val decimalInstance =
            DecimalInstanceModel(
                    id = id,
                    active = active,
                    creationDateTime = Instant.parse(creationDateTime),
                    internal = internal,
                    initJobId = jobId,
                    initJobName = jobName,
                    initJobInstanceNum = jobInstanceNum,
                    initFriendId = friendId
            )

    decimalInstance.value = value

    return decimalInstance
}

fun List<LocalDecimalInstance>.toExternal() = map(LocalDecimalInstance::toExternal)

fun DecimalInstanceModel.toLocal(): LocalDecimalInstance {
    return LocalDecimalInstance(
            id = id ?: 0,
            creationDateTime = creationDateTime.toString(),
            active = active,
            internal = internal,
            friendId = friendId,
            jobId = jobId,
            jobName = jobName,
            jobInstanceNum = jobInstanceNum,
            startDateTime = startDateTime?.toString(),
            value = value
    )
}

fun List<DecimalInstanceModel>.toLocal() = map(DecimalInstanceModel::toLocal)
