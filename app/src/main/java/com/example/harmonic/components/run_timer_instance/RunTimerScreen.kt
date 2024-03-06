package com.example.harmonic.components.run_timer_instance

import android.content.res.Configuration
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harmonic.models.instances.TimerInstanceModel
import com.example.harmonic.ui.theme.HarmonicTheme
import java.util.UUID


@Composable
fun RunTimerScreen(
    runTimerViewModel: RunTimerViewModel = hiltViewModel()
) {
    var instance = runTimerViewModel.instance.collectAsState(initial = TimerInstanceModel(
        id = UUID.randomUUID()
    ))

    TimerElement(timerValue = 0L,
        onStartClick = { /*TODO*/ },
        onPauseClick = { /*TODO*/ },
        onStopClick = { }
    )
}

@Composable
fun TimerElement(
    timerValue: Long,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = timerValue.formatTime(), fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onStartClick) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onPauseClick) {
                Text("Pause")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onStopClick) {
                Text("Stop")
            }
        }
    }
}

fun Long.formatTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}

@Preview(name = "Tracking light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Tracking dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RunTimerScreenPreview() {
    HarmonicTheme {
        RunTimerScreen()
    }
}