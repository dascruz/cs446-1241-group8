package com.example.harmonic.components.CounterJobList

import androidx.compose.runtime.Composable

@Composable
fun CounterJobListRoute(
    onGoToNewCounter: () -> Unit,
    onGoToEditCounterJob: (id: Int) -> Unit,
    onNavigateToAllCounterInstance: (idname: String) -> Unit
) {
    CounterJobListScreen(
        onGoToNewCounter = { onGoToNewCounter() },
        onNavigateToAllCounterInstance = { idname: String -> onNavigateToAllCounterInstance(idname) },
        onGoToEditCounterJob = { id: Int -> onGoToEditCounterJob(id) },
    )
}