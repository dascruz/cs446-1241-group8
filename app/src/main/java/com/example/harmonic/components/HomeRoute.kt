package com.example.harmonic.components

import androidx.compose.runtime.Composable

@Composable
fun HomeRoute(
    onGoToTracking: () -> Unit,
    onGoToInsights: () -> Unit
) {
    // ViewModel goes here if there is one
    HomeScreen(
        onGoToTracking = {onGoToTracking()},
        onGoToInsights = {onGoToInsights()}
    )
}