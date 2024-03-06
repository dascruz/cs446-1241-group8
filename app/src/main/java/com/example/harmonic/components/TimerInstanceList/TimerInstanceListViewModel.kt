package com.example.harmonic.components.TimerInstanceList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.data.TimerJob.TimerJobRepository
import com.example.harmonic.models.instances.TimerInstanceModel
import com.example.harmonic.models.jobs.TimerJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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
    val jobModel = timerJobRepository.observeById(jobId).stateIn(
        scope = viewModelScope,
        initialValue = TimerJobModel(id = jobId, name = ""),
        started = SharingStarted.WhileSubscribed(5000)
    )

    fun createNewTimerInstance(instanceNum: Int) {
        val newTimerInstance = TimerInstanceModel(
            id = UUID.randomUUID(),
            internal = true,
            initJobId = jobModel.value.id,
            initJobName = jobModel.value.name,
            initJobInstanceNum = instanceNum
        )

        viewModelScope.launch {
            timerInstanceRepository.createLocal(newTimerInstance)
        }
    }
}