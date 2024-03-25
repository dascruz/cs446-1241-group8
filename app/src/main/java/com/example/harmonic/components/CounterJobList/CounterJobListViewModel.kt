package com.example.harmonic.components.CounterJobList

import androidx.lifecycle.ViewModel
import com.example.harmonic.data.CounterJob.CounterJobRepository
import com.example.harmonic.models.jobs.CounterJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ViewModel for the counter job list screen
 */
@HiltViewModel
class CounterJobListViewModel @Inject constructor(
    private val counterJobRepository: CounterJobRepository,
) : ViewModel() {
    val allCounterInstanceFlow: Flow<List<CounterJobModel>> =
        counterJobRepository.observeAll()
}