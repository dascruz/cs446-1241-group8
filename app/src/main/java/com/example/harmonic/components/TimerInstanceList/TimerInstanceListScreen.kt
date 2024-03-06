package com.example.harmonic.components.TimerInstanceList
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harmonic.models.instances.TimerInstanceModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerInstanceListScreen(
    viewModel: TimerInstancesListViewModel = hiltViewModel(),
    onNavigateToNewTimerInstance: (id: UUID) -> Unit
) {
    val timerJobInstances by viewModel.allTimerInstancesFlow.collectAsState(initial = emptyList())
    val timerJob by viewModel.jobModel.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Instances of Job ${timerJob.id}") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.createNewTimerInstance(timerJobInstances.size)
                onNavigateToNewTimerInstance(timerJob.id)
            }) {
                Icon(Icons.Default.Add, contentDescription = "New")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (timerJobInstances.isEmpty()) {
                Text(
                    text = "No instances available for this job.",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(timerJobInstances.size) { index ->
                        TimerJobInstanceItem(instance = timerJobInstances[index])
                    }
                }
            }
        }
    }
}
@Composable
private fun TimerJobInstanceItem(instance: TimerInstanceModel) {
    //to be changed
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "${instance.jobName} ${instance.jobInstanceNum}",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Duration: ${instance.getTotalTime()}",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}