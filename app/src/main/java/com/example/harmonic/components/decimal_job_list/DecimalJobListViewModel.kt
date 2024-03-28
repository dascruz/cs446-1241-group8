package com.example.harmonic.components.decimal_job_list

import androidx.lifecycle.ViewModel
import com.example.harmonic.data.DecimalJob.DecimalJobRepository
import com.example.harmonic.models.jobs.DecimalJobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class DecimalJobListViewModel
@Inject
constructor(
        private val decimalJobRepository: DecimalJobRepository,
) : ViewModel() {
    val allDecimalInstanceFlow: Flow<List<DecimalJobModel>> = decimalJobRepository.observeAll()
}
