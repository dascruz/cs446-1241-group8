package com.example.harmonic.components.create_new_counter_job

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.fillMaxWidth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewCounterJobScreen(
    onGoToCounterJobs: () -> Unit,
    createNewCounterJobViewModel: CreateNewCounterJobViewModel = hiltViewModel()
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text(
                    text = "Create New Counter Job",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            },
            modifier = Modifier.padding(bottom = 50.dp)
        )

        var newCounterJobName by remember { mutableStateOf("") }
        Text(
            text = "Name:",
            style = MaterialTheme.typography.titleSmall
        )
        TextField(
            value = newCounterJobName,
            onValueChange = { newCounterJobName = it },
            label = { Text("New Counter Job Name") },
            modifier = Modifier.padding(bottom = 36.dp),
            singleLine = true
        )


        Button(
            modifier = Modifier.size(width = 80.dp, height = 80.dp),
            onClick = {
                createNewCounterJobViewModel.addNewCounterJob(newCounterJobName)
                onGoToCounterJobs()
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