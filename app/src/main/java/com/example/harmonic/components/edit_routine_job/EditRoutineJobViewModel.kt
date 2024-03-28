package com.example.harmonic.components.edit_routine_job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.RoutineJob.RoutineJobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditRoutineJobViewModel @Inject constructor(
    private val routineJobRepository: RoutineJobRepository
) : ViewModel() {
    fun editRoutineJob(id: Int, newName: String) {
        viewModelScope.launch {
            routineJobRepository.updateRoutineJob(id, newName)
        }
    }
}