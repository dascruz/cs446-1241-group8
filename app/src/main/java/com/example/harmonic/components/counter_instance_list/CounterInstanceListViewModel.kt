package com.example.harmonic.components.counter_instance_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.harmonic.data.CounterInstance.CounterInstanceRepository
import com.example.harmonic.models.instances.CounterInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ViewModel for the counter job instances list screen
 */
@HiltViewModel
class CounterInstancesListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val counterInstanceRepository: CounterInstanceRepository
) : ViewModel() {
    private val jobIdString: String = savedStateHandle.get<String>("jobId")!!
    val jobId: Int = jobIdString.toInt()
    val allCounterInstancesFlow: Flow<List<CounterInstanceModel>> = counterInstanceRepository.observeInstancesForJob(jobId)
    val jobName: String = savedStateHandle.get<String>("jobName")!!

    suspend fun createNewCounterInstance(instanceNum: Int): Int {
        println("Started creating new counter instance")
        val newCounterInstance = CounterInstanceModel(
            internal = true
        )
        newCounterInstance.updateJobInfo(
            id = jobId,
            name = jobName,
            num = instanceNum
        )

        val newKey = counterInstanceRepository.insertLocal(newCounterInstance)

        println("Created New Counter Instance with id $newKey")
        return newKey
    }

}