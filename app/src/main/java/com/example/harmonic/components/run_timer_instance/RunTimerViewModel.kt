package com.example.harmonic.components.run_timer_instance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.data.TimerJob.TimerJobRepository
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
    private val timerInstanceRepository: TimerInstanceRepository,
    private val timerJobRepository: TimerJobRepository
): ViewModel() {
    private val _durationText = MutableStateFlow(Duration.ZERO.toDisplayString())
    val durationText = _durationText.asStateFlow()
    private var timerJob: Job? = null

    private val instanceIdString: String = savedStateHandle.get<String>("instanceId")!!
    private val instanceId: Int = instanceIdString.toInt()
    private val _instance = MutableStateFlow(TimerInstanceModel(id = instanceId))
    val instance = _instance.asStateFlow()

    private val _segmentationType = MutableStateFlow("")
    val segmentationType = _segmentationType.asStateFlow()
    private val _segmentationValue = MutableStateFlow(1)
    val segmentationValue = _segmentationValue.asStateFlow()

    private val _startEnabled = MutableStateFlow(true)
    val startEnabled = _startEnabled.asStateFlow()
    private val _segmentEnabled = MutableStateFlow(false)
    val segmentEnabled = _segmentEnabled.asStateFlow()
    private val _stopEnabled = MutableStateFlow(false)
    val stopEnabled = _stopEnabled.asStateFlow()

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

                    if (_instance.value.jobId != null) {
                        timerJobRepository.getById(_instance.value.jobId!!).let { j ->
                            if (j != null) {
                                _segmentationType.value = j.segmentationType.toString()
                                _segmentationValue.value = j.segmentationValue
                            }
                        }
                    }
                } else {
                    throw NullPointerException("Instance not found")
                }
            }

            if (_instance.value.startDateTime != null) {
                print("restarting ${_instance.value.startDateTime}")
                restart(_instance.value.startDateTime!!)
            }
        }
    }

    private fun restart(startDateTime: Instant) {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _durationText.value = Duration.between(startDateTime, Instant.now()).toDisplayString()
            }
        }
        _startEnabled.value = false
        if (_segmentationType.value == "SET") {
            if (_segmentationValue.value > 1) {
                _segmentEnabled.value = true
            } else {
                _stopEnabled.value = true
            }
        } else if (_segmentationType.value == "CAP") {
            _stopEnabled.value = true
            if (_segmentationValue.value > 1) {
                _segmentEnabled.value = true
            }
        } else {
            _segmentEnabled.value = true
            _stopEnabled.value = true
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
        _startEnabled.value = false
        if (_segmentationType.value == "SET") {
            if (_segmentationValue.value > 1) {
                _segmentEnabled.value = true
            } else {
                _stopEnabled.value = true
            }
        } else if (_segmentationType.value == "CAP") {
            _stopEnabled.value = true
            if (_segmentationValue.value > 1) {
                _segmentEnabled.value = true
            }
        } else {
            _segmentEnabled.value = true
            _stopEnabled.value = true
        }
    }

    fun segment() {
        val currentTime = Instant.now()
        val segmentName = currentTime.toString()
        _instance.value.addSegment(currentTime, segmentName)
        viewModelScope.launch{
            timerInstanceRepository.upsertLocal(_instance.value)
        }
        if (_segmentationType.value == "CAP" &&
            _instance.value.getNumSegments() >= _segmentationValue.value - 1) {
            _segmentEnabled.value = false
        } else if (_segmentationType.value == "SET" &&
            _instance.value.getNumSegments() >= _segmentationValue.value - 1) {
            _segmentEnabled.value = false
            _stopEnabled.value = true
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