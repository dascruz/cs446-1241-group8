package com.example.harmonic.components.view_all_active

import androidx.lifecycle.ViewModel
import com.example.harmonic.data.RoutineInstance.RoutineInstanceRepository
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.models.IJobInstanceModel
import com.example.harmonic.models.instances.RoutineInstanceModel
import com.example.harmonic.models.instances.TimerInstanceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * UiState for the View All Active screen

data class ViewAllActiveUiState(
    val items: List<TimerInstanceModel> = emptyList(),
    val isLoading: Boolean = false
)*/

/**
 * ViewModel for the task list screen
 */
@HiltViewModel
class ViewAllActiveViewModel @Inject constructor(
    private val timerInstanceRepository: TimerInstanceRepository,
    private val routineInstanceRepository: RoutineInstanceRepository,
) : ViewModel() {
    val allActiveTimerInstanceFlow: Flow<List<IJobInstanceModel>> =
        timerInstanceRepository.observeActive()
    val allActiveRoutineInstanceFlow: Flow<List<IJobInstanceModel>> =
        routineInstanceRepository.observeActive()
}



