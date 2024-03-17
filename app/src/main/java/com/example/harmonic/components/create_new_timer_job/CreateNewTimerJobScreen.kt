package com.example.harmonic.components.create_new_timer_job


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewTimerJobScreen(
    onGoToTimerJobs: () -> Unit,
    createNewTimerJobViewModel: CreateNewTimerJobViewModel = hiltViewModel()
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text(
                    text = "Create New Timer",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            modifier = Modifier.padding(bottom = 50.dp)
        )

        var newTimerJobName by remember { mutableStateOf("") }
        Text(
            text = "Name:",
            style = MaterialTheme.typography.titleSmall
        )
        TextField(
            value = newTimerJobName,
            onValueChange = { newTimerJobName = it },
            label = { Text("New Timer Name") },
            modifier = Modifier.padding(bottom = 36.dp),
            singleLine = true
        )

        val radioOptions = listOf("None", "Capped", "Set Number")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
        var segmentationValue by remember { mutableStateOf("1") }
        Text(
            text = "Segmentation Restrictions:",
            style = MaterialTheme.typography.titleSmall
        )
        Column(
            modifier = Modifier.selectableGroup().padding(bottom = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            radioOptions.forEach { text ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
        if (selectedOption == "Capped" || selectedOption == "Set Number") {
            Text(
                text = "(Maximum) Number of Segments:",
                style = MaterialTheme.typography.titleSmall
            )
            TextField(
                value = segmentationValue,
                onValueChange = { text ->
                    if (text.isEmpty() || text.isDigitsOnly()) {
                        segmentationValue = text
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Number") },
                modifier = Modifier.padding(bottom = 36.dp),
                singleLine = true
            )

        }

        Button(
            modifier = Modifier.size(width = 80.dp, height = 80.dp),
            onClick = {
                createNewTimerJobViewModel.addNewTimerJob(newTimerJobName,
                    selectedOption, segmentationValue)
                onGoToTimerJobs()
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
