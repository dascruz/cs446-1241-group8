package com.example.harmonic.components.edit_timer_job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerJob.TimerJobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class EditTimerJobViewModel @Inject constructor(
    private val timerJobRepository: TimerJobRepository
) : ViewModel() {
    fun editTimerJob(id: UUID, newName: String) {
        viewModelScope.launch {
            timerJobRepository.updateTimerJob(id, newName)
        }
    }
}