package com.example.harmonic.components.create_new_routine_job

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
fun CreateNewRoutineJobScreen(
    onGoToRoutineJobs: () -> Unit,
    createNewRoutineJobViewModel: CreateNewRoutineJobViewModel = hiltViewModel()
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text(
                    text = "Create New Routine",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            modifier = Modifier.padding(bottom = 50.dp)
        )

        var newRoutineJobName by remember { mutableStateOf("") }
        Text(
            text = "Name:",
            style = MaterialTheme.typography.titleSmall
        )
        TextField(
            value = newRoutineJobName,
            onValueChange = { newRoutineJobName = it },
            label = { Text("New Routine Name") },
            modifier = Modifier.padding(bottom = 36.dp),
            singleLine = true
        )

        Button(
            modifier = Modifier.size(width = 80.dp, height = 80.dp),
            onClick = {
                createNewRoutineJobViewModel.addNewRoutineJob(newRoutineJobName)
                onGoToRoutineJobs()
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "New",
                modifier = Modifier.size(50.dp)
            )
        }

    }
}