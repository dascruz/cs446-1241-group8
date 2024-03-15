package com.example.harmonic.components.run_timer_instance

import androidx.compose.runtime.Composable

@Composable
fun RunTimerRoute(
    instanceIdString: String,
    onGoToHome: () -> Unit
) {
    RunTimerScreen(
        instanceId = instanceIdString.toInt(),
        onGoToHome = { onGoToHome() }
    )
}