package com.example.harmonic.components.TimerInstanceList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.models.instances.TimerInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ViewModel for the timer job instances list screen
 */
@HiltViewModel
class TimerInstancesListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val timerInstanceRepository: TimerInstanceRepository
) : ViewModel() {
    private val jobIdString: String = savedStateHandle.get<String>("jobId")!!
    val jobId: Int = jobIdString.toInt()
    val allTimerInstancesFlow: Flow<List<TimerInstanceModel>> = timerInstanceRepository.observeInstancesForJob(jobId)
    val jobName: String = savedStateHandle.get<String>("jobName")!!

    suspend fun createNewTimerInstance(instanceNum: Int): Int {
        println("Started creating new timer instance")
        val newTimerInstance = TimerInstanceModel(
            internal = true
        )
        newTimerInstance.updateJobInfo(
            id = jobId,
            name = jobName,
            num = instanceNum
        )

        val newKey = timerInstanceRepository.insertLocal(newTimerInstance)

        println("Created New Timer Instance with id $newKey")
        return newKey
    }
}