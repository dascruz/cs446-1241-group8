package com.example.harmonic.components.create_new_routine_instance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.RoutineInstance.RoutineInstanceRepository
import com.example.harmonic.models.instances.RoutineInstanceModel
import com.example.harmonic.models.instances.TimerInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class CreateNewRoutineInstanceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routineInstanceRepository: RoutineInstanceRepository
) : ViewModel() {

    private val instanceIdString: String = savedStateHandle.get<String>("instanceId")!!
    private val instanceId: Int = instanceIdString.toInt()
    private val _instance = MutableStateFlow(RoutineInstanceModel(id = instanceId))
    val instance = _instance.asStateFlow()

    suspend fun setStartTime(startTime : Int) {
        routineInstanceRepository.updateStartInstance(instanceId, startTime)
    }

    suspend fun setEndTime(endTime : Int){
        routineInstanceRepository.updateEndInstance(instanceId, endTime)
    }
}