package com.example.harmonic.models.instances

import com.example.harmonic.models.IJobInstanceModel
import java.time.Duration
import java.time.Instant
import java.util.UUID

class RoutineInstanceModel(
    override var jobId: UUID?,
    override var jobName: String?,
    override var jobInstanceNum: Int?,
    override var internal: Boolean = false,
    override var friendId: UUID? = UUID.randomUUID()
) : IJobInstanceModel {
    override val id: UUID = UUID.randomUUID()
    override var active = true;
    override val creationDateTime: Instant = Instant.now()
    var startDateTime: Instant? = null
        private set
    var endDateTime: Instant? = null
        private set

    fun getTotalTime() : Duration {
        return Duration.between(startDateTime, endDateTime)
    }
    override fun compareTo(other: IJobInstanceModel): Int {
        return if (other is RoutineInstanceModel && startDateTime != null && other.startDateTime != null) {
            startDateTime!!.compareTo(other.startDateTime)
        } else {
            super.compareTo(other)
        }
    }
}