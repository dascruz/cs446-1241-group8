package com.example.harmonic.components.tracking

import androidx.compose.runtime.Composable

@Composable
fun TrackingRoute(
    onGoToTimerJob: () -> Unit,
    onGoToRoutineJob: () -> Unit,
    onGoToCounterJob: () -> Unit,
    onGoToDecimalJob: () -> Unit
) {
    TrackingScreen(
        onGoToTimerJobs = { onGoToTimerJob() },
        onGoToRoutineJobs = { onGoToRoutineJob() },
        onGoToCounterJobs = { onGoToCounterJob() },
        onGoToDecimalJobs = { onGoToDecimalJob() }
    )
}