package com.example.harmonic.components.view_all_active

import androidx.lifecycle.ViewModel
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
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
) : ViewModel() {
    val allActiveTimerInstanceFlow: Flow<List<TimerInstanceModel>> =
        timerInstanceRepository.observeActive()
}


