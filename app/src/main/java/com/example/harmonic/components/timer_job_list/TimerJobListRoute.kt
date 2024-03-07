package com.example.harmonic.components.timer_job_list


import androidx.compose.runtime.Composable
import java.util.UUID

@Composable
fun TimerJobListRoute(
    onGoToNewTimer: () -> Unit,
    onGoToEditTimerJob: (id: UUID) -> Unit,
    onNavigateToAllTimerInstance: (idname: String) -> Unit
) {
    TimerJobListScreen(
        onGoToNewTimer = { onGoToNewTimer() },
        onNavigateToAllTimerInstance = { idname: String -> onNavigateToAllTimerInstance(idname) },
        onGoToEditTimerJob = { id: UUID -> onGoToEditTimerJob(id) },
    )
}