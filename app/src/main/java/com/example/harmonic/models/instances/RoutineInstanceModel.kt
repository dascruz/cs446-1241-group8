package com.example.harmonic.models.instances

import com.example.harmonic.models.IJobInstanceModel
import java.time.Duration
import java.time.Instant

class RoutineInstanceModel(
    override val id: Int? = null,
    override var active: Boolean = true,
    override val creationDateTime: Instant = Instant.now(),
    override var internal: Boolean = false,
    initStartDateTime: Int? = null,
    initEndDateTime: Int? = null,
    initJobId: Int? = null,
    initJobName: String? = null,
    initJobInstanceNum: Int? = null,
    initFriendId: Int? = null
) : IJobInstanceModel {
    override var jobId: Int? = null
    override var jobName: String? = null
    override var jobInstanceNum: Int? = null
    override var friendId: Int? = null
    override val instanceTypeString = "Routine"

    var startDateTime: Int? = null
    var endDateTime: Int? = null

    init {
        startDateTime = initStartDateTime
        endDateTime = initEndDateTime
        if (initFriendId != null) {
            friendId = initFriendId
        }

        if (initJobId != null && initJobName != null && initJobInstanceNum != null) {
            updateJobInfo(initJobId, initJobName, initJobInstanceNum)
        }
    }
    fun getTotalTime() : Int {
        return if (startDateTime != null && endDateTime != null) endDateTime!! - startDateTime!!
        else 0
    }
    /*override fun compareTo(other: IJobInstanceModel): Int {
        return if (other is RoutineInstanceModel && startDateTime != null && other.startDateTime != null) {
            startDateTime!!.compareTo(other.startDateTime)
        } else {
            super.compareTo(other)
        }
    }*/
}