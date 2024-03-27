package com.example.harmonic.components.edit_counter_job

import androidx.compose.runtime.Composable

@Composable
fun EditCounterJobRoute(
    jobIdString: String,
    onGoToCounterJobs: () -> Unit
) {
    EditCounterJobScreen(
        jobId = jobIdString.toInt(),
        onGoToCounterJobs = { onGoToCounterJobs() }
    )
}