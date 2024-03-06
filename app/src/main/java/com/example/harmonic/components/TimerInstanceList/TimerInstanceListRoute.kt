package com.example.harmonic.components.TimerInstanceList


import androidx.compose.runtime.Composable
import java.util.UUID

@Composable
fun TimerInstanceListRoute(
    jobIdString: String,
    onNavigateToNewTimerInstance: (id: UUID) -> Unit
) {
    TimerInstanceListScreen(
        onNavigateToNewTimerInstance = { id: UUID -> onNavigateToNewTimerInstance(id) }
    )
}