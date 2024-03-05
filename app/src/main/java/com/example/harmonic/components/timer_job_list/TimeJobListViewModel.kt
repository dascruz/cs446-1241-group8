package com.example.harmonic.components.view_all_active

import androidx.lifecycle.ViewModel
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.models.instances.TimerInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ViewModel for the timer job list screen
 */
@HiltViewModel
class TimerJobListViewModel @Inject constructor(
    private val timerInstanceRepository: TimerInstanceRepository,
) : ViewModel() {
    val allTimerInstanceFlow: Flow<List<TimerInstanceModel>> =
        timerInstanceRepository.observeAll()
}



