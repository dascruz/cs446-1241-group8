package com.example.harmonic.components.edit_timer_job

import androidx.compose.runtime.Composable

@Composable
fun EditTimerJobRoute(
    jobIdString: String,
    onGoToTimerJobs: () -> Unit
) {
    EditTimerJobScreen(
        jobId = jobIdString.toInt(),
        onGoToTimerJobs = { onGoToTimerJobs() }
    )
}