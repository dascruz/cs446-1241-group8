package com.example.harmonic.components.view_all_active

import androidx.compose.runtime.Composable

@Composable
fun ViewAllActiveRoute(
    onNavigateToActiveTimerInstance: (id: Int) -> Unit,
    onNavigateToActiveRoutineInstance: (id: Int) -> Unit,
    onNavigateToActiveDecimalInstance: (id: Int) -> Unit,
    onNavigateToActiveCounterInstance: (id: Int) -> Unit
) {
    ViewAllActiveScreen(
        onNavigateToActiveTimerInstance = { id: Int -> onNavigateToActiveTimerInstance(id) },
        onNavigateToActiveRoutineInstance = { id: Int -> onNavigateToActiveRoutineInstance(id) },
        onNavigateToActiveDecimalInstance = { id: Int -> onNavigateToActiveDecimalInstance(id) },
        onNavigateToActiveCounterInstance = { id: Int -> onNavigateToActiveCounterInstance(id) }
    )
}