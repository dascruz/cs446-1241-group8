package com.example.harmonic.components

import androidx.compose.runtime.Composable

@Composable
fun TimerRoute(
    onGoToNewTimer: () -> Unit
) {
    TimerScreen(
        onGoToNewTimer = { onGoToNewTimer() }
    )
}