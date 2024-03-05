package com.example.harmonic.components.timer_job_list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
// import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// import com.example.harmonic.components.view_all_active.ActiveInstanceItem
import com.example.harmonic.components.view_all_active.TimerJobListViewModel
import com.example.harmonic.components.view_all_active.ViewAllActiveScreen
import com.example.harmonic.components.view_all_active.ViewAllActiveViewModel
import com.example.harmonic.models.IJobInstanceModel
import com.example.harmonic.models.instances.TimerInstanceModel
import com.example.harmonic.ui.theme.HarmonicTheme
import java.util.UUID

@Composable
fun TimerJobListScreen(
    onGoToNewTimer: () -> Unit,
    onNavigateToAllTimerInstance: (id: UUID) -> Unit,
    viewModel: TimerJobListViewModel = hiltViewModel()
) {
    val allTimerInstances by viewModel.allTimerInstanceFlow.collectAsState(initial = emptyList())
    // Navigation if needed
    val onGoToAllTimerInstances = { instance: IJobInstanceModel ->
        when(instance) {
            is TimerInstanceModel -> onNavigateToAllTimerInstance(instance.id)
            else -> {}
        }
    }
    TimerJobListScreen(
        onGoToAllTimerInstances = onGoToAllTimerInstances,
        allTimerInstances = allTimerInstances,
        onGoToNewTimer = onGoToNewTimer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerJobListScreen(

    onGoToNewTimer: () -> Unit,
    onGoToAllTimerInstances: (instance: IJobInstanceModel) -> Unit,
    allTimerInstances: List<IJobInstanceModel>
) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
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
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(allTimerInstances) { ti ->
                    TimerInstanceItem(onGoToAllTimerInstances = onGoToAllTimerInstances, item = ti)
                }
            }
        }
    }
}

@Composable
private fun TimerInstanceItem(
    onGoToAllTimerInstances: (instance: IJobInstanceModel) -> Unit,
    item: IJobInstanceModel
) {
    Row(
        // verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onGoToAllTimerInstances(item) }
    ) {
        Text(
            text = item.getInstanceJobString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp).weight(1f)
        )
        EditButton(modifier = Modifier)
        ShareButton(modifier = Modifier)
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
fun TimerJobListScreenPreview() {
    HarmonicTheme {
        Surface {
            TimerJobListScreen(
                onGoToNewTimer = {/* TODO */},
                onGoToAllTimerInstances = {},
                allTimerInstances = listOf(
                    TimerInstanceModel(
                        id = UUID.randomUUID(),
                        initJobId = UUID.randomUUID(),
                        initJobName = "Test Job A",
                        initJobInstanceNum = 0
                    ),
                    TimerInstanceModel(
                        id = UUID.randomUUID(),
                        initJobId = UUID.randomUUID(),
                        initJobName = "Test Job B",
                        initJobInstanceNum = 1
                    ),
                    TimerInstanceModel(
                        id = UUID.randomUUID(),
                        initJobId = UUID.randomUUID(),
                        initJobName = "Test Job C",
                        initJobInstanceNum = 2
                    ),
                    TimerInstanceModel(
                        id = UUID.randomUUID(),
                        initJobId = UUID.randomUUID(),
                        initJobName = "Test Job D",
                        initJobInstanceNum = 3
                    ),
                    TimerInstanceModel(
                        id = UUID.randomUUID(),
                        initJobId = UUID.randomUUID(),
                        initJobName = "Test Job E",
                        initJobInstanceNum = 3
                    ),
                    TimerInstanceModel(
                        id = UUID.randomUUID(),
                        initJobId = UUID.randomUUID(),
                        initJobName = "Test Job F",
                        initJobInstanceNum = 5
                    )
                )
            )
        }
    }
}