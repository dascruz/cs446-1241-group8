package com.example.harmonic.components.timer_job_list


import androidx.compose.runtime.Composable

@Composable
fun TimerJobListRoute(
    onGoToNewTimer: () -> Unit,
    onGoToEditTimerJob: (id: Int) -> Unit,
    onNavigateToAllTimerInstance: (idname: String) -> Unit
) {
    TimerJobListScreen(
        onGoToNewTimer = { onGoToNewTimer() },
        onNavigateToAllTimerInstance = { idname: String -> onNavigateToAllTimerInstance(idname) },
        onGoToEditTimerJob = { id: Int -> onGoToEditTimerJob(id) },
    )
}