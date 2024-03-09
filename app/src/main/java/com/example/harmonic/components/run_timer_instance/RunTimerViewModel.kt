package com.example.harmonic.components.run_timer_instance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.util.toDisplayString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RunTimerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val timerInstanceRepository: TimerInstanceRepository
): ViewModel() {
    private val _durationText = MutableStateFlow(Duration.ZERO.toDisplayString())
    val durationText = _durationText.asStateFlow()
    private var timerJob: Job? = null

    val instanceIdString: String = savedStateHandle.get<String>("instanceId")!!
    val instanceId: UUID = UUID.fromString(instanceIdString)
    val instance = timerInstanceRepository.observeInstance(instanceId)/*.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = TimerInstanceModel(id = instanceId)
    )*/


    init {
        println("Initializing Run Timer View Model")
        viewModelScope.launch {
            instance.collect{
                println("${it.jobName} found")
            }
        }
    }

    fun start() {
        val startDateTime = Instant.now()
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

    fun segment(final: Boolean) {
        val currentTime = Instant.now()
        val segmentName = currentTime.toString()
        viewModelScope.launch{
            timerInstanceRepository.updateActiveInstance(instanceId, !final, currentTime, segmentName)
        }
    }

    fun stop() {
        segment(true)
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}