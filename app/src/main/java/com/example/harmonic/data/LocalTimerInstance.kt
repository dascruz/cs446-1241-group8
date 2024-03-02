package com.example.harmonic.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "TimerInstance"
)
data class LocalTimerInstance(
    @PrimaryKey val id: UUID,
    val creationDateTime: String,
    var active: Boolean,
    var internal: Boolean,
    var friendId: UUID,
    var jobId: UUID,
    var jobName: String,
    var jobInstanceNum: Int,
    var startDateTime: String,
    var segments: String,
    var segmentNames: String
)