package com.example.harmonic.components.home

import androidx.compose.runtime.Composable

@Composable
fun HomeRoute(
    onGoToTracking: () -> Unit,
    onGoToAllActive: () -> Unit,
    onGoToInsights: () -> Unit
) {
    // ViewModel goes here if there is one
    HomeScreen(
        onGoToTracking = {onGoToTracking()},
        onGoToAllActive = {onGoToAllActive()},
        onGoToInsights = {onGoToInsights()}
    )
}