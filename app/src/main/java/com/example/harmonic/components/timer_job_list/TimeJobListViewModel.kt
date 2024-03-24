package com.example.harmonic.components.view_all_active

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.data.TimerJob.TimerJobRepository
import com.example.harmonic.models.jobs.TimerJobModel
import com.example.harmonic.services.SharingService
import com.example.harmonic.services.shareables.toShareable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the timer job list screen
 */
@HiltViewModel
class TimerJobListViewModel @Inject constructor(
    private val timerJobRepository: TimerJobRepository,
    private val timerInstanceRepository: TimerInstanceRepository,
    private val sharingService: SharingService
) : ViewModel() {
    val allTimerInstanceFlow: Flow<List<TimerJobModel>> =
        timerJobRepository.observeAll()

    private val _sharedJobId = MutableStateFlow("")
    val sharedJobId = _sharedJobId.asStateFlow()

    fun shareJob(job: TimerJobModel) {
        viewModelScope.launch {
            timerInstanceRepository.observeInstancesForJob(job.id!!).collect {
                _sharedJobId.value = sharingService.sendTimer(job.toShareable(), it.toShareable())
            }
        }
    }

    fun doneSharingJob() {
        _sharedJobId.value = ""
    }
}



