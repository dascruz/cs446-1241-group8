package com.example.harmonic.components.create_new_timer_job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerJob.TimerJobRepository
import com.example.harmonic.models.jobs.TimerJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNewTimerJobViewModel @Inject constructor(
    private val timerJobRepository: TimerJobRepository,
) : ViewModel() {
    fun addNewTimerJob(newTimerJobName: String) {
        viewModelScope.launch {
            timerJobRepository.createLocal(TimerJobModel(name = newTimerJobName))
        }
    }
}