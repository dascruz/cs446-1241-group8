package com.example.harmonic.components.edit_counter_job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonic.data.CounterJob.CounterJobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCounterJobViewModel @Inject constructor(
    private val counterJobRepository: CounterJobRepository
) : ViewModel() {
    fun editCounterJob(id: Int, newName: String) {
        viewModelScope.launch {
            counterJobRepository.updateCounterJob(id, newName)
        }
    }
}