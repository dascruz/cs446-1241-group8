package com.example.harmonic.components.TimerInstanceList


import androidx.compose.runtime.Composable
import com.example.harmonic.models.IJobInstanceModel
import java.util.UUID

@Composable
fun TimerInstanceListRoute(
    job : String,
    onNavigateToAllTimerInstance: (job : String) -> Unit
) {
    TimerInstanceListScreen(
        jobId = UUID.fromString(job),
        onNavigateToAllTimerInstance = onNavigateToAllTimerInstance
    )
}