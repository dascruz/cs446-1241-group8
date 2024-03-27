package com.example.harmonic.components.run_routine_instance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.RoutineInstance.RoutineInstanceRepository
import com.example.harmonic.models.instances.RoutineInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class CreateNewRoutineInstanceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routineInstanceRepository: RoutineInstanceRepository,
): ViewModel() {

    private val instanceIdString: String = savedStateHandle.get<String>("instanceId")!!
    private val instanceId: Int = instanceIdString.toInt()
    private val _instance = MutableStateFlow(RoutineInstanceModel(id = instanceId))
    val instance = _instance.asStateFlow()


    private val _startEnabled = MutableStateFlow(true)
    val startEnabled = _startEnabled.asStateFlow()
    private val _stopEnabled = MutableStateFlow(false)
    val stopEnabled = _stopEnabled.asStateFlow()

    init {
        println("Initializing Run Routine View Model")
        refresh()
        println("id from instance: ${_instance.value.id}, id from SSH: $instanceId")
    }

    fun refresh() {
        viewModelScope.launch {
            routineInstanceRepository.getInstance(instanceId).let { i ->
                if (i != null) {
                    _instance.value = i
                } else {
                    throw NullPointerException("Instance not found")
                }
            }

            if (_instance.value.startDateTime != null) {
                print("restarting ${_instance.value.startDateTime}")
                restart()
            }
        }
    }

    private fun restart() {
        _startEnabled.value = false
        _stopEnabled.value = true
    }

    fun start() {
        val startDateTime = Instant.now()
        viewModelScope.launch {
            routineInstanceRepository.updateStartInstance(instanceId, startDateTime)
        }
        _startEnabled.value = false
        _stopEnabled.value = true
    }

    fun stop() {
        val endDateTime = Instant.now()
        _stopEnabled.value = false
        viewModelScope.launch {
            routineInstanceRepository.updateEndInstance(instanceId, endDateTime)
            routineInstanceRepository.makeNotActive(instanceId)
        }
    }
}