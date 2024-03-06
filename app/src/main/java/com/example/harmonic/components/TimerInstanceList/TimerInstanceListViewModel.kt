package com.example.harmonic.components.TimerInstanceList
import androidx.lifecycle.ViewModel
import com.example.harmonic.data.TimerJob.TimerJobRepository
import com.example.harmonic.models.jobs.TimerJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ViewModel for the timer job instances list screen
 */
@HiltViewModel
class TimerInstancesListViewModel @Inject constructor(
    private val timerJobRepository: TimerJobRepository
) : ViewModel() {
    val allTimerInstancesFlow: Flow<List<TimerJobModel>> = timerJobRepository.observeAll()
}