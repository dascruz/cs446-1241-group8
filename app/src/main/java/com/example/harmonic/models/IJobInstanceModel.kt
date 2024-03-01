package com.example.harmonic.models

import java.time.Instant
import java.util.UUID

interface IJobInstanceModel : Comparable<IJobInstanceModel> {
    val id: UUID
    val creationDateTime: Instant
    var active: Boolean
    var internal: Boolean
    var friendId: UUID
    var jobId: UUID
    var jobName: String
    var jobInstanceNum: Int

    fun updateInstanceInfo(id: UUID, name: String, num: Int): Unit {
        this.jobId = id
        this.jobName = name
        this.jobInstanceNum = num
    }

    override fun compareTo(other: IJobInstanceModel): Int {
        return creationDateTime.compareTo(other.creationDateTime)
    }
}