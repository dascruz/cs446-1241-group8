package com.example.harmonic.components.view_all_active

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harmonic.models.IJobInstanceModel
import com.example.harmonic.models.instances.TimerInstanceModel

@Composable
fun ViewAllActiveScreen(
    onNavigateToActiveTimerInstance: (id: Int) -> Unit,
    onNavigateToActiveRoutineInstance: (id: Int) -> Unit,
    onNavigateToActiveDecimalInstance: (id: Int) -> Unit,
    onNavigateToActiveCounterInstance: (id: Int) -> Unit,
    viewModel: ViewAllActiveViewModel = hiltViewModel()
) {
    val activeInstances by viewModel.allActiveTimerInstanceFlow.collectAsState(initial = emptyList())
    val onGoToActiveInstance = { instance: IJobInstanceModel ->
        when(instance) {
            is TimerInstanceModel -> onNavigateToActiveTimerInstance(instance.id!!)
            else -> {}
        }
    }
    ViewAllActiveScreen(
        onGoToActiveInstance = onGoToActiveInstance,
        activeInstances = activeInstances
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAllActiveScreen(
    onGoToActiveInstance: (instance: IJobInstanceModel) -> Unit,
    activeInstances: List<IJobInstanceModel>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text("Go to an active instance:")
                }
            )
        }
    ) {padding ->
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
                items(activeInstances) { ti ->
                    ActiveInstanceItem(onGoToActiveInstance = onGoToActiveInstance, item = ti)
                }
            }
        }
    }
}


@Composable
private fun ActiveInstanceItem(
    onGoToActiveInstance: (ti: IJobInstanceModel) -> Unit,
    item: IJobInstanceModel
) {
    Card(
        // verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onGoToActiveInstance(item) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Text(
            text = "${item.instanceTypeString}: ${item.getInstanceJobString()} ",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

/*
@Preview
@Composable
private fun ViewAllActiveScreenPreview() {
    HarmonicTheme {
        Surface {
            ViewAllActiveScreen(
                onGoToActiveInstance = {},
                activeInstances = listOf(
                    TimerInstanceModel(
                        id = Int.randomInt(),
                        initJobId = Int.randomInt(),
                        initJobName = "Test Job A",
                        initJobInstanceNum = 0
                    ),
                    TimerInstanceModel(
                        id = Int.randomInt(),
                        initJobId = Int.randomInt(),
                        initJobName = "Test Job B",
                        initJobInstanceNum = 1
                    ),
                    TimerInstanceModel(
                        id = Int.randomInt(),
                        initJobId = Int.randomInt(),
                        initJobName = "Test Job C",
                        initJobInstanceNum = 2
                    ),
                    TimerInstanceModel(
                        id = Int.randomInt(),
                        initJobId = Int.randomInt(),
                        initJobName = "Test Job D",
                        initJobInstanceNum = 3
                    ),
                    TimerInstanceModel(
                        id = Int.randomInt(),
                        initJobId = Int.randomInt(),
                        initJobName = "Test Job E",
                        initJobInstanceNum = 3
                    ),
                    TimerInstanceModel(
                        id = Int.randomInt(),
                        initJobId = Int.randomInt(),
                        initJobName = "Test Job F",
                        initJobInstanceNum = 5
                    )
                )
            )
        }
    }
}*/