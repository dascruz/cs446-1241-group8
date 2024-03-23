package com.example.harmonic.services.impl

import com.example.harmonic.models.instances.TimerInstanceModel
import com.example.harmonic.models.jobs.TimerJobModel
import com.example.harmonic.services.SharingService
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SharingServiceImpl
@Inject
constructor(private val firestore: FirebaseFirestore): SharingService {
    override suspend fun getTimerJob(id: String): TimerInstanceModel? {
        TODO("Not yet implemented")
    }

    override suspend fun getInstancesForJob(id: String): List<TimerJobModel>? {
        TODO("Not yet implemented")
    }
}