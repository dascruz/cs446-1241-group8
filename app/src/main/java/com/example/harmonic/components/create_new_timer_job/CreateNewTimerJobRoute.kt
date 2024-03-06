package com.example.harmonic.components.create_new_timer_job

import androidx.compose.runtime.Composable


@Composable
fun CreateNewTimerJobRoute(
    onGoToTimerJob: () -> Unit,
) {
    CreateNewTimerJobScreen(onGoToTimerJobs = { onGoToTimerJob() })
}