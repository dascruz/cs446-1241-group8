package com.example.harmonic.components.timer_job_list


import androidx.compose.runtime.Composable
import java.util.UUID

@Composable
fun TimerJobListRoute(
    onGoToNewTimer: () -> Unit,
    onGoToEditTimerJob: (id: UUID) -> Unit,
    onNavigateToAllTimerInstance: (id: UUID) -> Unit
) {
    TimerJobListScreen(
        onGoToNewTimer = { onGoToNewTimer() },
        onNavigateToAllTimerInstance = { id: UUID -> onNavigateToAllTimerInstance(id) },
        onGoToEditTimerJob = { id: UUID -> onGoToEditTimerJob(id) },
    )
}