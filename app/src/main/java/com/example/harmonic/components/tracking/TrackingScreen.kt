package com.example.harmonic.components.tracking

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.harmonic.ui.theme.HarmonicTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackingScreen(
    onGoToTimerJobs: () -> Unit,
    onGoToRoutineJobs: () -> Unit,
    onGoToCounterJobs: () -> Unit,
    onGoToDecimalJobs: () -> Unit
) {
    Scaffold(
        topBar = {
                 TopAppBar(
                     colors = topAppBarColors(
                         containerColor = MaterialTheme.colorScheme.primaryContainer,
                         titleContentColor = MaterialTheme.colorScheme.primary
                     ),
                     title = {
                         Text("Select a Job Type:")
                     }
                 )
        },
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Card(
                onClick = { onGoToTimerJobs() },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier.fillMaxSize()
                    .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Start Timer Job",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                )
                Text(
                    text = "Start a timer and record segments of time. Good for recording how long " +
                            "it takes to travel, order food, accomplish tasks, and more!",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp),
                )
            }

            Card(
                onClick = { onGoToRoutineJobs() },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier.fillMaxSize()
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Start Routine Job",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                )
                Text(
                    text = "Have a timely routine you complete regularly? Track how well you" +
                            " stick to it in terms of time and date! " +
                            "Meal times, self-care, study, etc.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp),
                )
            }

            Card(
                onClick = { onGoToCounterJobs() },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier.fillMaxSize()
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Start Counter Job",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                )
                Text(
                    text = "Just the basics. One number, and the ability to increase or decrease it." +
                            " If you just need to count, or just need numbers, this is it.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp),
                )
            }

            Card(
                onClick = { onGoToDecimalJobs() },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier.fillMaxSize()
                    .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Start Decimal/Monetary Job",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                )
                Text(
                    text = "Similar to Counter, but with decimal numbers. Specify the number of" +
                            "decimal figures, 2 for monetary values. Good for tracking prices!",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}

@Preview(name = "Tracking light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Tracking dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TrackingScreenPreview() {
    HarmonicTheme {
        TrackingScreen(
            onGoToTimerJobs = {},
            onGoToRoutineJobs = {},
            onGoToCounterJobs = {},
            onGoToDecimalJobs = {}
        )
    }
}
