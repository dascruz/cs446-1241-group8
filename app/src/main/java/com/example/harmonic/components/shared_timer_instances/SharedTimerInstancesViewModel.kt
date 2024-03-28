package com.example.harmonic.components.shared_timer_instances

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.data.TimerJob.TimerJobRepository
import com.example.harmonic.models.instances.TimerInstanceModel
import com.example.harmonic.models.jobs.SegmentationTypes
import com.example.harmonic.services.SharingService
import com.example.harmonic.services.shareables.toExternal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedTimerInstancesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val timerInstanceRepository: TimerInstanceRepository,
    private val timerJobRepository: TimerJobRepository,
    private val sharingService: SharingService
) : ViewModel() {
    private val jobIdString: String = savedStateHandle.get<String>("jobId")!!
    private val jobName: String = savedStateHandle.get<String>("jobName")!!
    val jobId: Int = jobIdString.toInt()
    val sharedTimerInstancesFlow: Flow<List<TimerInstanceModel>> = timerInstanceRepository.observeSharedInstancesForJob(jobId)

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()
    private val _statusMessage = MutableStateFlow("")
    val statusMessage = _statusMessage.asStateFlow()

    private val _segmentationType = MutableStateFlow(SegmentationTypes.NONE)
    private val _segmentationValue = MutableStateFlow(1)

    init {
        viewModelScope.launch {
            timerJobRepository.getById(jobId).let { j ->
                if (j != null) {
                    _segmentationType.value = j.segmentationType
                    _segmentationValue.value = j.segmentationValue
                }
            }
        }
    }

    fun addSharedInstances(code: String) {
        viewModelScope.launch {
            _loading.value = true
            val timerJobShareable = sharingService.getTimerJob(code)
            if (timerJobShareable != null) {
                val timerJob = timerJobShareable.toExternal(jobName)
                if (timerJob.segmentationType == _segmentationType.value &&
                    timerJob.segmentationValue == _segmentationValue.value) {
                    sharingService.getInstancesForJob(code).map {
                        it.toExternal(jobId, jobName)
                    }.collect {
                        timerInstanceRepository.insertAll(it)
                        _loading.value = false
                        _statusMessage.value = "Successfully added shared instances!"
                    }
                    _loading.value = false
                    _statusMessage.value = "Successfully added shared instances!"
                } else {
                    _loading.value = false
                    _statusMessage.value = "Shared job does not match this job's parameters."
                }
            } else {
                _loading.value = false
                _statusMessage.value = "Unable to find job with code $code"
            }
        }
    }

    fun reset() {
        _loading.value = false
        _statusMessage.value = ""
    }
}