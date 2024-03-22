package com.example.harmonic.components.routine_instance_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.RoutineInstance.RoutineInstanceRepository
import com.example.harmonic.models.instances.RoutineInstanceModel
import com.example.harmonic.models.jobs.TimerJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineInstancesListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routineInstanceRepository: RoutineInstanceRepository
) : ViewModel() {
    private val jobIdString: String = savedStateHandle.get<String>("jobId")!!
    val jobId: Int = jobIdString.toInt()
    val allRoutineInstancesFlow: Flow<List<RoutineInstanceModel>> = routineInstanceRepository.observeInstancesForJob(jobId)
    val jobName: String = savedStateHandle.get<String>("jobName")!!

    fun createNewRoutineInstance(instanceNum: Int) {
        println("Started creating new routine instance")
        val newRoutineInstance = RoutineInstanceModel(
            internal = true
        )
        newRoutineInstance.updateJobInfo(
            id = jobId,
            name = jobName,
            num = instanceNum
        )


        viewModelScope.launch {
            routineInstanceRepository.insertLocal(newRoutineInstance)
        }

    }
}