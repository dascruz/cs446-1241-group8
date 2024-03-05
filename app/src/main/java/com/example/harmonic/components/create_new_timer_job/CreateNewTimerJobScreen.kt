package com.example.harmonic.components.create_new_timer_job


import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.example.harmonic.data.TimerInstance.TimerInstanceRepository
import com.example.harmonic.data.TimerJob.TimerJobRepository
import com.example.harmonic.models.jobs.TimerJobModel




@Composable
fun CreateNewTimerJobScreen() {
    val timerJobRepository: TimerJobRepository
    Column {
        Text(
            text = "New Timer Job",
            style = MaterialTheme.typography.titleMedium,
        )
        var newTimerJobName by remember { mutableStateOf("") }
        TextField(
            value = newTimerJobName,
            onValueChange = { newTimerJobName = it },
            label = { Text("Name") }
        )

        Button(onClick = { /* timerJobRepository.createLocal(TimerJobModel(name = newTimerJobName)) */}) {
            Icon(Icons.Default.Add, contentDescription = "New")
        }

    }
}