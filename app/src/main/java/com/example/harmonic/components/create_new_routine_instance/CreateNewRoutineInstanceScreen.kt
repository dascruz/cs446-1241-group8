package com.example.harmonic.components.create_new_routine_instance

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewRoutineInstanceScreen(
    viewModel: CreateNewRoutineInstanceViewModel = hiltViewModel(),
    instanceId: Int,
    onNavigateToInstanceList: () -> Unit
) {
    val composableScope = rememberCoroutineScope()

    val startTimeState = rememberTimePickerState()
    val endTimeState = rememberTimePickerState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Enter Start Time")
        TimeInput(
            state = startTimeState,
        )
        Text(text = "Enter End Time")
        TimeInput(
            state = endTimeState,
        )

        Button(onClick = {
            composableScope.launch {
                viewModel.setStartTime(startTimeState.hour * 60 + startTimeState.minute)
                viewModel.setEndTime(endTimeState.hour * 60 + endTimeState.minute)
                onNavigateToInstanceList()
            }
        }) {
            Icon(Icons.Default.Add, contentDescription = "New")
        }
    }
}
