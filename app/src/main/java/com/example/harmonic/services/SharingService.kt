package com.example.harmonic.services

import com.example.harmonic.services.shareables.TimerInstanceShareable
import com.example.harmonic.services.shareables.TimerJobShareable
import kotlinx.coroutines.flow.Flow

interface SharingService {
    suspend fun getTimerJob(id: String): TimerJobShareable?
    suspend fun getInstancesForJob(id: String): Flow<List<TimerInstanceShareable>>
    suspend fun sendTimer(job: TimerJobShareable, instancesList: List<TimerInstanceShareable>): String
}