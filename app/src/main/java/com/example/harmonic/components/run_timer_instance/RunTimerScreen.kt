package com.example.harmonic.components.run_timer_instance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harmonic.util.toDisplayString
import java.time.Duration
import java.time.Instant


@Composable
fun RunTimerScreen(
    instanceId: Int,
    onGoToHome: () -> Unit,
    runTimerViewModel: RunTimerViewModel = hiltViewModel()
) {
    runTimerViewModel.refresh()
    val durationText by runTimerViewModel.durationText.collectAsState()
    val timerInstance by runTimerViewModel.instance.collectAsState()
    val startEnabled by runTimerViewModel.startEnabled.collectAsState()
    val segmentEnabled by runTimerViewModel.segmentEnabled.collectAsState()
    val stopEnabled by runTimerViewModel.stopEnabled.collectAsState()
    val segmentationType by runTimerViewModel.segmentationType.collectAsState()
    val segmentationValue by runTimerViewModel.segmentationValue.collectAsState()

    val segmentTitle = if (segmentationType == "CAP" || segmentationType == "SET") {
        "Segments ($segmentationType, $segmentationValue):"
    } else {
        "Segments:"
    }

    TimerElement(timerValue = durationText,
        jobName = timerInstance.getName(),
        segments = timerInstance.getSegments(),
        segmentTitle = segmentTitle,
        startInstant = timerInstance.startDateTime,
        startEnabled = startEnabled,
        segmentEnabled = segmentEnabled,
        stopEnabled = stopEnabled,
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
    segments: List<Instant>,
    segmentTitle: String,
    startInstant: Instant?,
    startEnabled: Boolean,
    segmentEnabled: Boolean,
    stopEnabled: Boolean,
    onStartClick: () -> Unit,
    onSegmentClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = jobName, style = MaterialTheme.typography.titleLarge )
            Text(text = timerValue, style = MaterialTheme.typography.displayLarge )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = onStartClick, enabled = startEnabled) {
                    Text("Start", style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onSegmentClick, enabled = segmentEnabled) {
                    Text("Segment", style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onStopClick, enabled = stopEnabled) {
                    Text("Stop", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = segmentTitle, style = MaterialTheme.typography.titleMedium)
            LazyColumn(
                //contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                //verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(segments.size) { index ->
                    SegmentsListItem(
                        index = index,
                        segmentInstant = segments[index],
                        startInstant = startInstant
                    )
                }
            }
        }
    }
}

@Composable
private fun SegmentsListItem(index: Int, segmentInstant: Instant, startInstant: Instant?) {
    if (startInstant != null) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Segment ${index + 1} —", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = Duration.between(startInstant, segmentInstant).toDisplayString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}