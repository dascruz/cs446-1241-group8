package com.example.harmonic.components.counter_instance_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@Composable
fun CounterInstanceListRoute(
    jobIdString: String,
    jobName: String,
    onNavigateToNewCounterInstance: (id: Int) -> Unit,
    viewModel: CounterInstancesListViewModel = hiltViewModel()
) {

    CounterInstanceListScreen(
        jobId = jobIdString.toInt(),
        jobName = jobName,
        onNavigateToNewCounterInstance = { id: Int -> onNavigateToNewCounterInstance(id) }
    )
}