package com.example.harmonic.components.run_timer_instance

import androidx.compose.runtime.Composable
import java.util.UUID

@Composable
fun RunTimerRoute(
    instanceIdString: String
) {
    RunTimerScreen(
        instanceId = UUID.fromString(instanceIdString)
    )
}