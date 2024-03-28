package com.example.harmonic.components.rountine_job_list


import androidx.compose.runtime.Composable

@Composable
fun RoutineJobListRoute(
    onGoToNewRoutine: () -> Unit,
    onGoToEditRoutineJob: (id: Int) -> Unit,
    onNavigateToAllRoutineInstance: (idname: String) -> Unit
) {
    RoutineJobListScreen(
        onGoToNewRoutine = { onGoToNewRoutine() },
        onNavigateToAllRoutineInstance = { idname: String -> onNavigateToAllRoutineInstance(idname) },
        onGoToEditRoutineJob = { id: Int -> onGoToEditRoutineJob(id) },
    )
}