package com.example.harmonic.components.TimerInstanceList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.models.instances.TimerInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
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

    fun createNewTimerInstance(instanceNum: Int) {
        println("Started creating new timer instance")
        val newTimerInstance = TimerInstanceModel(
            internal = true
        )
        newTimerInstance.updateJobInfo(
            id = jobId,
            name = jobName,
            num = instanceNum
        )

        viewModelScope.launch {
            timerInstanceRepository.createLocal(newTimerInstance)
        }

        println("Created New Timer Instance")
    }
}