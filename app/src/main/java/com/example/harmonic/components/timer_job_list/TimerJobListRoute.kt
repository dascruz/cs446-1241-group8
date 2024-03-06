package com.example.harmonic.components.timer_job_list


import androidx.compose.runtime.Composable
import java.util.UUID
import com.example.harmonic.components.TimerInstanceList.TimerInstanceListRoute

@Composable
fun TimerJobListRoute(
    onGoToNewTimer: () -> Unit,
    onNavigateToAllTimerInstance: (id: UUID) -> Unit
) {
    TimerJobListScreen(
        onGoToNewTimer = { onGoToNewTimer() },
        onNavigateToAllTimerInstance = onNavigateToAllTimerInstance
    )
}