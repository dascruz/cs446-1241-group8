package com.example.harmonic.models.instances

import com.example.harmonic.models.IJobInstanceModel
import java.time.Duration
import java.time.Instant
import java.util.UUID

class TimerInstanceModel(
    override var internal: Boolean = false,
    override var friendId: UUID = UUID.randomUUID()
) : IJobInstanceModel {
    override val id: UUID = UUID.randomUUID()
    override val creationDateTime: Instant = Instant.now()
    var startDateTime: Instant? = null
        private set
    private val segments: MutableList<Duration> = mutableListOf()
    private val segmentNames: List<String> = mutableListOf()

    fun getTotalTime() : Duration {
        return segments.fold(Duration.ZERO) { acc: Duration, t: Duration -> acc + t }
    }

    fun getSegments(): List<Duration> {
        return segments as List<Duration>
    }

     fun addSegment(instant: Instant) : Unit {
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
     }

    override fun compareTo(other: IJobInstanceModel): Int {
        return if (other is TimerInstanceModel && startDateTime != null && other.startDateTime != null) {
            startDateTime!!.compareTo(other.startDateTime)
        } else {
            super.compareTo(other)
        }
    }
}