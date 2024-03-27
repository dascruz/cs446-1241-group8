package com.example.harmonic.components.edit_counter_job

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCounterJobScreen(
    jobId: Int,
    onGoToCounterJobs: () -> Unit,
    editNewCounterJobViewModel: EditCounterJobViewModel = hiltViewModel()
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text(
                    text = "Edit Counter Name",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            modifier = Modifier.padding(bottom = 50.dp)
        )

        var newCounterJobName by remember { mutableStateOf("") }
        TextField(
            value = newCounterJobName,
            onValueChange = { newCounterJobName = it },
            label = { Text("New Counter Name") },
            singleLine = true,
            modifier = Modifier.padding(bottom = 50.dp)
        )

        Button(
            modifier = Modifier.size(width = 80.dp, height = 80.dp),
            onClick = {
                editNewCounterJobViewModel.editCounterJob(jobId, newCounterJobName)
                onGoToCounterJobs()
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                Icons.Default.Edit,
                contentDescription = "Edit",
                modifier = Modifier.size(50.dp)
            )
        }

    }
}