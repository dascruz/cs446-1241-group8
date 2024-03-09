package com.example.harmonic.models.instances

import com.example.harmonic.models.IJobInstanceModel
import java.time.Duration
import java.time.Instant

class TimerInstanceModel(
    override val id: Int? = null,
    override var active: Boolean = true,
    override val creationDateTime: Instant = Instant.now(),
    override var internal: Boolean = false,
    initStartDateTime: Instant? = null,
    initJobId: Int? = null,
    initJobName: String? = null,
    initJobInstanceNum: Int? = null,
    initFriendId: Int? = null
) : IJobInstanceModel {
    var startDateTime: Instant? = null
    private val segments: MutableList<Instant> = mutableListOf()
    private val segmentNames: MutableList<String> = mutableListOf()
    override var jobId: Int? = null
    override var jobName: String? = null
    override var jobInstanceNum: Int? = null
    override var friendId: Int? = null

    init {
        startDateTime = initStartDateTime
        if (initFriendId != null) {
            friendId = initFriendId
        }

        if (initJobId != null && initJobName != null && initJobInstanceNum != null) {
            updateJobInfo(initJobId, initJobName, initJobInstanceNum)
        }
    }

    fun getTotalTime() : Duration {
        if (startDateTime == null || segments.size == 0) {
            return Duration.ZERO
        }
        return Duration.between(segments.last(), startDateTime)
    }

    fun getSegments(): List<Instant> {
        return segments
    }

    fun getSegmentNames(): List<String> {
        return segmentNames
    }


     fun addSegment(instant: Instant, name: String) {
         if (startDateTime == null) {
             throw NullPointerException()
         }
         val totalTime = this.getTotalTime()
         val lastSegmentEnd : Instant = startDateTime!! + totalTime
         if (instant < lastSegmentEnd) {
             throw Exception("Invalid segment")
         }
         segments.add(instant)
         segmentNames.add(name) // TODO: check input
     }

    fun addSegments(instants: List<Instant>, names: List<String>) {
        if (instants.size != names.size) {
            throw Exception("List of segment Instants and segment names should be the same size")
        }
        if (startDateTime == null) {
            throw NullPointerException()
        }
        for (i in instants.indices) {
            addSegment(instants[i], names[i])
        }
    }

    fun renameSegment(name: String, index: Int) {
        if (index >= 0 && index < segmentNames.size) {
            segmentNames[index] = name
        }
    }

    override fun compareTo(other: IJobInstanceModel): Int {
        return if (other is TimerInstanceModel && startDateTime != null && other.startDateTime != null) {
            startDateTime!!.compareTo(other.startDateTime)
        } else {
            super.compareTo(other)
        }
    }
}