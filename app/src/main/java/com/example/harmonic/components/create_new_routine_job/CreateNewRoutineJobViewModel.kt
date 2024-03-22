package com.example.harmonic.components.create_new_routine_job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.RoutineJob.RoutineJobRepository
import com.example.harmonic.models.jobs.RoutineJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNewRoutineJobViewModel @Inject constructor(
    private val routineJobRepository: RoutineJobRepository,
) : ViewModel() {
    fun addNewRoutineJob(
        newRoutineJobName: String,
    ) {
        viewModelScope.launch {
            routineJobRepository.createLocal(
                RoutineJobModel(
                    name = newRoutineJobName,
                )
            )
        }
    }
}