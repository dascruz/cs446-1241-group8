package com.example.harmonic.components.edit_routine_job

import androidx.compose.runtime.Composable

@Composable
fun EditRoutineJobRoute(
    jobIdString: String,
    onGoToRoutineJobs: () -> Unit
) {
    EditRoutineJobScreen(
        jobId = jobIdString.toInt(),
        onGoToRoutineJobs = { onGoToRoutineJobs() }
    )
}