package com.example.harmonic.components.create_new_counter_job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.CounterJob.CounterJobRepository
import com.example.harmonic.models.jobs.SegmentationTypes
import com.example.harmonic.models.jobs.CounterJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNewCounterJobViewModel @Inject constructor(
    private val counterJobRepository: CounterJobRepository,
) : ViewModel() {
    fun addNewCounterJob(
        newCounterJobName: String
    ){
        viewModelScope.launch {
            counterJobRepository.createLocal(
                CounterJobModel( name = newCounterJobName)
            )
        }
    }
}