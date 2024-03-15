package com.example.harmonic.components.run_timer_instance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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

    private val _instance = MutableStateFlow(TimerInstanceModel(id = instanceId))
    val instance = _instance.asStateFlow()


    init {
        println("Initializing Run Timer View Model")
        refresh()
        println("id from instance: ${_instance.value.id}, id from SSH: $instanceId")
    }

    fun refresh() {
        viewModelScope.launch {
            timerInstanceRepository.getInstance(instanceId).let { i ->
                if (i != null) {
                    _instance.value = i
                } else {
                    throw NullPointerException("Instance not found")
                }
            }
        }
    }

    fun start() {
        val startDateTime = Instant.now()
        _instance.value.startDateTime = startDateTime
        viewModelScope.launch {
            timerInstanceRepository.updateStartInstance(instanceId, startDateTime)
        }
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _durationText.value = Duration.between(startDateTime, Instant.now()).toDisplayString()
            }
        }
    }

    fun segment() {
        val currentTime = Instant.now()
        val segmentName = currentTime.toString()
        _instance.value.addSegment(currentTime, segmentName)
        viewModelScope.launch{
            timerInstanceRepository.upsertLocal(_instance.value)
        }
    }

    fun stop() {
        _instance.value.active = false
        segment()
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}