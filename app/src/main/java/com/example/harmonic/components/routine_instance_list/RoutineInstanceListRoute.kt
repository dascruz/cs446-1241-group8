package com.example.harmonic.components.routine_instance_list

import androidx.compose.runtime.Composable

@Composable
fun RoutineInstanceListRoute(
    jobIdString: String,
    jobName: String,
    onNavigateToNewRoutineInstance: (id: Int) -> Unit
) {
    RoutineInstanceListScreen(
        jobId = jobIdString.toInt(),
        jobName = jobName,
        onNavigateToNewRoutineInstance = { id: Int -> onNavigateToNewRoutineInstance(id) }
    )
}