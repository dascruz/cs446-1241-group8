package com.example.harmonic.components.shared_timer_instances

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harmonic.models.instances.TimerInstanceModel
import com.example.harmonic.util.toDisplayString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedTimerInstancesScreen(
    viewModel: SharedTimerInstancesViewModel = hiltViewModel(),
    jobName: String
) {
    val sharedTimerInstances by viewModel.sharedTimerInstancesFlow.collectAsState(initial = emptyList())
    val loading by viewModel.loading.collectAsState()
    val statusMessage by viewModel.statusMessage.collectAsState()
    var dialogOpen by remember{ mutableStateOf(false) }
    var codeEntered by remember{ mutableStateOf(false) }
    var code by remember{ mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Shared Instances of Job $jobName") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                dialogOpen = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "New")
            }
        }
    ) { padding ->
        Column (
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (dialogOpen) {
                Dialog(
                    onDismissRequest = {
                        dialogOpen = false
                        codeEntered = false
                        viewModel.reset()
                    }
                ) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(250.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            if (!codeEntered && !loading) {
                                Text(
                                    text = "Enter Code:",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                TextField(
                                    value = code,
                                    onValueChange = { code = it },
                                    label = { Text("Sharing Code") },
                                    singleLine = true,
                                    modifier = Modifier.padding(bottom = 50.dp)
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    TextButton(
                                        onClick = {
                                            codeEntered = true
                                            viewModel.addSharedInstances(code.trim())
                                        },
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        Text("Enter")
                                    }
                                }
                            } else if (codeEntered && loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.width(64.dp),
                                    color = MaterialTheme.colorScheme.secondary,
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                )
                            } else if (codeEntered) {
                                Text(
                                    text = statusMessage,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(16.dp)
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    TextButton(
                                        onClick = {
                                            dialogOpen = false
                                            codeEntered = false
                                            viewModel.reset()
                                        },
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        Text("Dismiss")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (sharedTimerInstances.isEmpty()) {
                Text(
                    text = "No shared instances available for this job.",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sharedTimerInstances.size) { index ->
                        TimerJobInstanceItem(instance = sharedTimerInstances[index])
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
            text = "Duration: \n ${instance.getTotalTime().toDisplayString()}",
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}