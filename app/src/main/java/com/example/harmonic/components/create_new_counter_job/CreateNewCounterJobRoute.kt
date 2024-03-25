package com.example.harmonic.components.create_new_counter_job


import androidx.compose.runtime.Composable


@Composable
fun CreateNewCounterJobRoute(
    onGoToCounterJob: () -> Unit,
) {
    CreateNewCounterJobScreen(onGoToCounterJobs = { onGoToCounterJob() })
}