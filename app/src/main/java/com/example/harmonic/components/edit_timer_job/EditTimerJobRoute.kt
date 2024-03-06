package com.example.harmonic.components.edit_timer_job

import androidx.compose.runtime.Composable
import java.util.UUID

@Composable
fun EditTimerJobRoute(
    jobIdString: String,
    onGoToTimerJobs: () -> Unit
) {
    EditTimerJobScreen(
        jobId = UUID.fromString(jobIdString),
        onGoToTimerJobs = { onGoToTimerJobs() }
    )
}