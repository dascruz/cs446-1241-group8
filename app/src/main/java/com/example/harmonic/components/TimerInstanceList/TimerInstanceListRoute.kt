package com.example.harmonic.components.TimerInstanceList


import androidx.compose.runtime.Composable

@Composable
fun TimerInstanceListRoute(
    jobIdString: String,
    jobName: String,
    onNavigateToNewTimerInstance: (id: Int) -> Unit,
    onNavigateToShared: (idname: String) -> Unit
) {
    TimerInstanceListScreen(
        jobId = jobIdString.toInt(),
        jobName = jobName,
        onNavigateToNewTimerInstance = { id: Int -> onNavigateToNewTimerInstance(id) },
        onNavigateToShared = { idname -> onNavigateToShared(idname) }
    )
}