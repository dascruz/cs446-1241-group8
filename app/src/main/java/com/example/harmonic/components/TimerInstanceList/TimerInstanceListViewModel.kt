package com.example.harmonic.components.TimerInstanceList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.data.TimerJob.TimerJobRepository
import com.example.harmonic.models.instances.TimerInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * ViewModel for the timer job instances list screen
 */
@HiltViewModel
class TimerInstancesListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val timerJobRepository: TimerJobRepository,
    private val timerInstanceRepository: TimerInstanceRepository
) : ViewModel() {
    private val jobIdString: String = savedStateHandle.get<String>("jobId")!!
    val jobId: UUID = UUID.fromString(jobIdString)
    val allTimerInstancesFlow: Flow<List<TimerInstanceModel>> = timerInstanceRepository.observeInstancesForJob(jobId)
    val jobName = MutableStateFlow<String>("default")
    private val jobModel = timerJobRepository.observeById(jobId)

    fun createNewTimerInstance(instanceNum: Int) {
        val newTimerInstance = TimerInstanceModel(
            id = UUID.randomUUID(),
            internal = true,
        )
        newTimerInstance.updateJobInfo(
            id = jobId,
            name = jobName.value,
            num = instanceNum
        )

        viewModelScope.launch {
            timerInstanceRepository.createLocal(newTimerInstance)
        }
    }
}