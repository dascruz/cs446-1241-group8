package com.example.harmonic.components.run_timer_instance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.models.instances.TimerInstanceModel
import com.example.harmonic.util.toDisplayString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class RunTimerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val timerInstanceRepository: TimerInstanceRepository
): ViewModel() {
    private val _durationText = MutableStateFlow(Duration.ZERO.toDisplayString())
    val durationText = _durationText.asStateFlow()
    private var timerJob: Job? = null

    private val instanceIdString: String = savedStateHandle.get<String>("instanceId")!!
    private val instanceId: Int = instanceIdString.toInt()
    val instance = timerInstanceRepository.observeInstance(instanceId).asLiveData()
    private val _startDateTime = MutableStateFlow(Instant.now())
    private val _jobName = MutableStateFlow("default")

    init {
        println(instance.value?.jobName)
    }

    fun start(instance: TimerInstanceModel) {
       _startDateTime.value = Instant.now()
        instance.startDateTime = _startDateTime.value
        println(instance.jobName)
        viewModelScope.launch {
            timerInstanceRepository.createLocal(instance)
        }
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _durationText.value = Duration.between(_startDateTime.value, Instant.now()).toDisplayString()
            }
        }
    }

    fun segment(instance: TimerInstanceModel) {
        val currentTime = Instant.now()
        val segmentName = currentTime.toString()
        instance.addSegment(currentTime, segmentName)
        viewModelScope.launch{
            timerInstanceRepository.createLocal(instance)
        }
    }

    fun stop(instance: TimerInstanceModel) {
        segment(instance)
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}