package com.example.harmonic.components.run_timer_instance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RunTimerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val timerInstanceRepository: TimerInstanceRepository
): ViewModel() {
    val instanceIdString: String = savedStateHandle.get<String>("instanceId")!!
    val instanceId: UUID = UUID.fromString(instanceIdString)
    val instance = timerInstanceRepository.observeInstance(instanceId).asLiveData()

    fun start() {
        val startDateTime = Instant.now()
        viewModelScope.launch {
            timerInstanceRepository.updateStartInstance(instanceId, startDateTime)
        }
    }
    fun update(final: Boolean) {
        val currentTime = Instant.now()
        val segmentName = currentTime.toString()
        viewModelScope.launch{
            timerInstanceRepository.updateActiveInstance(instanceId, final, currentTime, segmentName)
        }
    }
}