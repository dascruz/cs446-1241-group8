package com.example.harmonic.models

import java.time.Instant

interface IJobInstanceModel : Comparable<IJobInstanceModel> {
    val id: Int?
    val creationDateTime: Instant
    var active: Boolean
    var internal: Boolean
    var friendId: Int?
    var jobId: Int?
    var jobName: String?
    var jobInstanceNum: Int?
    val instanceTypeString: String

    fun updateJobInfo(id: Int, name: String, num: Int) {
        this.jobId = id
        this.jobName = name
        this.jobInstanceNum = num
    }

    fun getInstanceJobString(): String {
        if (this.jobId != null && this.jobName != null && this.jobInstanceNum != null) {
            return "${this.jobName!!} ${this.jobInstanceNum!! + 1}"
        }
        else {
            throw NullPointerException()
        }
    }

    override fun compareTo(other: IJobInstanceModel): Int {
        return creationDateTime.compareTo(other.creationDateTime)
    }
}