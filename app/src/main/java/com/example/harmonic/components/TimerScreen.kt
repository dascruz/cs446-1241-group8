package com.example.harmonic.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
// import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.harmonic.ui.theme.HarmonicTheme
import java.util.Timer
import androidx.compose.material3.Button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    onGoToNewTimer: () -> Unit
) {
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text("Your Timers")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onGoToNewTimer }) {
                Icon(Icons.Default.Add, contentDescription = "New")
            }
        }
    ) { innerPadding ->
        Column(
            // verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(innerPadding)

                .height(IntrinsicSize.Max)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) { Row {
                Text(
                    text = "Assignment 2 - CS 486",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(16.dp).weight(1f),
                )
                EditButton(modifier = Modifier
                    .padding(5.dp))
                ShareButton(modifier = Modifier
                    .padding(5.dp))
            }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) { Row {
                Text(
                    text = "Folding Laundry",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(16.dp).weight(1f),
                )
                EditButton(modifier = Modifier
                    .padding(5.dp))
                ShareButton(modifier = Modifier
                    .padding(5.dp))
            }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) { Row {
                Text(
                    text = "Assignment 4 - CS 480",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(16.dp).weight(1f)
                )
                EditButton(modifier = Modifier
                    .padding(5.dp))
                ShareButton(modifier = Modifier
                    .padding(5.dp))
            }
            }
        }
    }
}


@Composable
fun EditButton(modifier: Modifier) {
    Button(onClick = {
        //your onclick code here
    }) {
        Text(text = "Edit")
    }
}
@Composable
fun ShareButton(modifier: Modifier) {
    Button(onClick = {
        //your onclick code here
    }) {
        Text(text = "Share")
    }
}




@Preview(name = "Timer light theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Timer dark theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TimerScreenPreview() {
    HarmonicTheme {
        TimerScreen(
            onGoToNewTimer = {}
        )
    }
}