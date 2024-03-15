package com.example.harmonic.components.TimerInstanceList


import androidx.compose.runtime.Composable

@Composable
fun TimerInstanceListRoute(
    jobIdString: String,
    jobName: String,
    onNavigateToNewTimerInstance: (id: Int) -> Unit
) {
    TimerInstanceListScreen(
        jobId = jobIdString.toInt(),
        jobName = jobName,
        onNavigateToNewTimerInstance = { id: Int -> onNavigateToNewTimerInstance(id) }
    )
}