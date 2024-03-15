package com.example.harmonic.components.run_timer_instance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun RunTimerScreen(
    instanceId: Int,
    onGoToHome: () -> Unit,
    runTimerViewModel: RunTimerViewModel = hiltViewModel()
) {
    runTimerViewModel.refresh()
    val durationText by runTimerViewModel.durationText.collectAsState()
    val timerInstance = runTimerViewModel.instance.collectAsState()

    TimerElement(timerValue = durationText,
        jobName = timerInstance.value.getName(),
        onStartClick = { runTimerViewModel.start() },
        onSegmentClick = { runTimerViewModel.segment() },
        onStopClick = {
            runTimerViewModel.stop()
            onGoToHome()
        }
    )
}

@Composable
fun TimerElement(
    timerValue: String,
    jobName: String,
    onStartClick: () -> Unit,
    onSegmentClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = jobName, fontSize = 24.sp )
        Text(text = timerValue, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onStartClick) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onSegmentClick) {
                Text("Segment")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onStopClick) {
                Text("Stop")
            }
        }
    }
}