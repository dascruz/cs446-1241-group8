package com.example.harmonic.components.run_routine_instance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateNewRoutineInstanceScreen(
    instanceId: Int,
    onGoToHome: () -> Unit,
    viewModel: CreateNewRoutineInstanceViewModel = hiltViewModel()
) {
    val routineInstance by viewModel.instance.collectAsState()
    val startEnabled by viewModel.startEnabled.collectAsState()
    val stopEnabled by viewModel.stopEnabled.collectAsState()

    RoutineElement(
        jobName = routineInstance.getName(),
        startEnabled = startEnabled,
        stopEnabled = stopEnabled,
        onStartClick = { viewModel.start() },
        onStopClick = {
            viewModel.stop()
            onGoToHome()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineElement(
    jobName: String,
    startEnabled: Boolean,
    stopEnabled: Boolean,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text(
                    text = "Routine Instance",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            modifier = Modifier.padding(bottom = 50.dp)
        )

        Button(onClick = onStartClick, enabled = startEnabled) {
            Text("Start Routine Instance", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onStopClick, enabled = stopEnabled) {
            Text("End Routine Instance", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

