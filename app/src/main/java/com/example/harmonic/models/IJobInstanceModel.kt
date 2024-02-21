package com.example.harmonic.models

import java.time.Instant
import java.util.UUID

interface IJobInstanceModel : Comparable<IJobInstanceModel> {
    val id: UUID
    val creationDateTime: Instant
    var internal: Boolean
    var friendId: UUID

    override fun compareTo(other: IJobInstanceModel): Int {
        return creationDateTime.compareTo(other.creationDateTime)
    }
}