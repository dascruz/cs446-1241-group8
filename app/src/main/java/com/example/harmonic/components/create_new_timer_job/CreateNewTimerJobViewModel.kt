package com.example.harmonic.components.create_new_timer_job

import androidx.lifecycle.ViewModel
import com.example.harmonic.data.TimerJob.TimerJobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNewTimerJobViewModel @Inject constructor(
    private val timerJobRepository: TimerJobRepository,
) : ViewModel() {
}