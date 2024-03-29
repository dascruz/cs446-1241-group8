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
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harmonic.models.instances.TimerInstanceModel
import com.example.harmonic.util.toDisplayString
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerInstanceListScreen(
    viewModel: TimerInstancesListViewModel = hiltViewModel(),
    jobId: Int,
    jobName: String,
    onNavigateToNewTimerInstance: (id: Int) -> Unit,
    onNavigateToShared: (idname: String) -> Unit
) {
    val timerJobInstances by viewModel.internalTimerInstancesFlow.collectAsState(initial = emptyList())
    val composableScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Instances of Job $jobName") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary

                ),
                actions = {
                    IconButton(
                        onClick = { onNavigateToShared("${jobId}/${jobName}") }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "Shared"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                composableScope.launch {
                    val newId = viewModel.createNewTimerInstance(timerJobInstances.size)
                    onNavigateToNewTimerInstance(newId)
                }
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = instance.getInstanceJobString(),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Duration: \n ${if (instance.active) "Active" else instance.getTotalTime().toDisplayString()}",
            color = if (instance.active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}