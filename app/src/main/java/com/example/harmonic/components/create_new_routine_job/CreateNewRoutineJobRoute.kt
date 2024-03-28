package com.example.harmonic.components.create_new_routine_job

import androidx.compose.runtime.Composable


@Composable
fun CreateNewRoutineJobRoute(
    onGoToRoutineJob: () -> Unit,
) {
    CreateNewRoutineJobScreen(onGoToRoutineJobs = { onGoToRoutineJob() })
}