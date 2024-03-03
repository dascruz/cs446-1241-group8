package com.example.harmonic.models.instances

import com.example.harmonic.models.IJobInstanceModel
import java.time.Duration
import java.time.Instant
import java.util.UUID

class TimerInstanceModel(
    override val id: UUID,
    override var active: Boolean = true,
    override val creationDateTime: Instant = Instant.now(),
    override var internal: Boolean = false,
    initStartDateTime: Instant? = null,
    initJobId: UUID? = null,
    initJobName: String? = null,
    initJobInstanceNum: Int? = null,
    initFriendId: UUID? = null
) : IJobInstanceModel {
    var startDateTime: Instant? = null
        private set
    private val segments: MutableList<Duration> = mutableListOf()
    private val segmentNames: MutableList<String> = mutableListOf()
    override var jobId: UUID? = null
    override var jobName: String? = null
    override var jobInstanceNum: Int? = null
    override var friendId: UUID? = null

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
        return segments.fold(Duration.ZERO) { acc: Duration, t: Duration -> acc + t }
    }

    fun getSegments(): List<Duration> {
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
         val diff = Duration.between(instant, lastSegmentEnd)
         segments.add(diff)
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