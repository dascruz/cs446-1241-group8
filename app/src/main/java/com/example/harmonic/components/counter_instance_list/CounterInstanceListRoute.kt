package com.example.harmonic.components.counter_instance_list

import androidx.compose.runtime.Composable

@Composable
fun CounterInstanceListRoute(
    jobIdString: String,
    jobName: String,
    onNavigateToNewCounterInstance: (id: Int) -> Unit
) {
    CounterInstanceListScreen(
        jobId = jobIdString.toInt(),
        jobName = jobName,
        onNavigateToNewCounterInstance = { id: Int -> onNavigateToNewCounterInstance(id) }
    )
}