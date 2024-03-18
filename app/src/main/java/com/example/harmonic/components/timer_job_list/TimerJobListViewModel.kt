package com.example.harmonic.components.timer_job_list

import androidx.lifecycle.ViewModel
import com.example.harmonic.data.TimerJob.TimerJobRepository
import com.example.harmonic.models.jobs.TimerJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ViewModel for the timer job list screen
 */
@HiltViewModel
class TimerJobListViewModel @Inject constructor(
    private val timerJobRepository: TimerJobRepository,
) : ViewModel() {
    val allTimerInstanceFlow: Flow<List<TimerJobModel>> =
        timerJobRepository.observeAll()
}



