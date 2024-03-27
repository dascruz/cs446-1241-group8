package com.example.harmonic.models.instances

import com.example.harmonic.models.IJobInstanceModel
import java.time.Instant

class CounterInstanceModel(
    override val id: Int? = null,
    override var active: Boolean = true,
    override val creationDateTime: Instant = Instant.now(),
    override var internal: Boolean = false,
    initialValue: Int = 0,
    initStartDateTime: Instant? = null,
    initJobId: Int? = null,
    initJobName: String? = null,
    initJobInstanceNum: Int? = null,
    initFriendId: Int? = null
) : IJobInstanceModel {
    override var friendId: Int? = null
    override var jobId: Int? = null
    override var jobName: String? = null
    override var jobInstanceNum: Int? = null
    override val instanceTypeString = "Counter"

    var count: Int = initialValue

    init {

        if (initFriendId != null) {
            friendId = initFriendId
        }

        if (initJobId != null && initJobName != null && initJobInstanceNum != null) {
            updateJobInfo(initJobId, initJobName, initJobInstanceNum)
        }
    }
    init {
        if (initialValue < 0) {
            throw IllegalArgumentException("Initial value cannot be negative")
        }
    }

    fun retrieveCount(newCount: Int) : Int {
        if (newCount < 0) {
            throw IllegalArgumentException("Count cannot be negative")
        }
        count = newCount
        return count
    }
}