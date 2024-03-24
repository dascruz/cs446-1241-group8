package com.example.harmonic.services.impl

import com.example.harmonic.services.SharingService
import com.example.harmonic.services.shareables.TimerInstanceShareable
import com.example.harmonic.services.shareables.TimerJobShareable
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SharingServiceImpl
@Inject
constructor(private val firestore: FirebaseFirestore): SharingService {
    override suspend fun getTimerJob(id: String): TimerJobShareable? {
        return firestore.collection(TIMER_JOB_COLLECTION)
            .document(id)
            .get()
            .await()
            .toObject<TimerJobShareable?>()
    }

    override suspend fun getInstancesForJob(id: String): Flow<List<TimerInstanceShareable>> {
        return firestore.collection(TIMER_JOB_COLLECTION)
            .document(id)
            .collection(TIMER_INSTANCE_COLLECTION)
            .dataObjects<TimerInstanceShareable>()
    }

    override suspend fun sendTimer(job: TimerJobShareable, instancesList: List<TimerInstanceShareable>): String {
        val id = firestore.collection(TIMER_JOB_COLLECTION).add(job).await().id
        val instancesRef = firestore.collection(TIMER_JOB_COLLECTION).document(id).collection(TIMER_INSTANCE_COLLECTION)
        val batch = firestore.batch()
        instancesList.forEach {
            val docRef = instancesRef.document()
            batch.set(docRef, it)
        }
        batch.commit().await()
        return id
    }

    companion object {
        private const val TIMER_JOB_COLLECTION = "timerJobs"
        private const val TIMER_INSTANCE_COLLECTION = "timerInstances"
    }
}