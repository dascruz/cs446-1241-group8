package com.example.harmonic.components.rountine_job_list

import androidx.lifecycle.ViewModel
import com.example.harmonic.data.RoutineInstance.RoutineInstanceRepository
import com.example.harmonic.data.RoutineJob.RoutineJobRepository
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.data.TimerJob.TimerJobRepository
import com.example.harmonic.models.jobs.RoutineJobModel
import com.example.harmonic.models.jobs.TimerJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ViewModel for the routine job list screen
 */
@HiltViewModel
class RoutineJobListViewModel @Inject constructor(
    private val routineJobRepository: RoutineJobRepository,
) : ViewModel() {
    val allRoutineInstanceFlow: Flow<List<RoutineJobModel>> =
        routineJobRepository.observeAll()
}
