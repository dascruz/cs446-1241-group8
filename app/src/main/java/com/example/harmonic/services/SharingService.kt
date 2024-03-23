package com.example.harmonic.services

import com.example.harmonic.models.instances.TimerInstanceModel
import com.example.harmonic.models.jobs.TimerJobModel

interface SharingService {
    suspend fun getTimerJob(id: String): TimerInstanceModel?
    suspend fun getInstancesForJob(id: String): List<TimerJobModel>?
}