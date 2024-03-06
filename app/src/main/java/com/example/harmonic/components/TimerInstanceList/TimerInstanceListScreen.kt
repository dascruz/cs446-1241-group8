package com.example.harmonic.components.TimerInstanceList
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.example.harmonic.models.IJobInstanceModel
import com.example.harmonic.models.jobs.TimerJobModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerInstanceListScreen(
    jobId: UUID,
    viewModel: TimerInstancesListViewModel = hiltViewModel(),
    onNavigateToAllTimerInstance: (job: String) -> Unit
) {
    val timerJobInstances by viewModel.allTimerInstancesFlow.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Instances of Job") },
            )
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
private fun TimerJobInstanceItem(instance: TimerJobModel) {
    //to be changed
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            //to be changed once screen works
            text = "Instance ID: ${instance.id}",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Duration: ",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}