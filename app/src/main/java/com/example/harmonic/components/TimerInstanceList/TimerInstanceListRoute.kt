package com.example.harmonic.components.TimerInstanceList


import androidx.compose.runtime.Composable
import java.util.UUID

@Composable
fun TimerInstanceListRoute(
    jobIdString: String,
    jobName: String,
    onNavigateToNewTimerInstance: (id: UUID) -> Unit
) {
    TimerInstanceListScreen(
        jobId = UUID.fromString(jobIdString),
        jobName = jobName,
        onNavigateToNewTimerInstance = { id: UUID -> onNavigateToNewTimerInstance(id) }
    )
}