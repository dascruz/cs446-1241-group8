package com.example.harmonic.components.CounterJobList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.harmonic.components.timer_job_list.EditButton
import com.example.harmonic.components.timer_job_list.ShareButton
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.jobs.CounterJobModel



@Composable
fun CounterJobListScreen(
    onGoToNewCounter: () -> Unit,
    onNavigateToAllCounterInstance: (idname: String) -> Unit,
    onGoToEditCounterJob: (id: Int) -> Unit,
    viewModel: CounterJobListViewModel = hiltViewModel()
) {
    val allCounterJobs by viewModel.allCounterInstanceFlow.collectAsState(initial = emptyList())
    // Navigation if needed
    val onGoToAllCounterJobs = { job: IJobModel ->
        when(job) {
            is CounterJobModel -> onNavigateToAllCounterInstance(job.id.toString() + "/" + job.name)
            else -> {}
        }
    }
    val onEndCounterJob: (Int) -> Unit = { jobId ->
        viewModel.viewModelScope.launch {
            viewModel.endJob(jobId)
        }
    }
    CounterJobListScreen(
        onGoToAllCounterJobs = onGoToAllCounterJobs,
        allCounterJobs = allCounterJobs,
        onGoToNewCounter = onGoToNewCounter,
        onNavigateToCounterJobInstances = onNavigateToAllCounterInstance,
        onGoToEditCounterJob = onGoToEditCounterJob,
        onEndCounterJob = onEndCounterJob
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterJobListScreen(

    onGoToNewCounter: () -> Unit,
    onGoToEditCounterJob: (id: Int) -> Unit,
    onGoToAllCounterJobs: (job: IJobModel) -> Unit,
    onNavigateToCounterJobInstances: (idname: String) -> Unit,
    onEndCounterJob : (id: Int) -> Unit,
    allCounterJobs: List<IJobModel>
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
                    Text("Your Counters")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onGoToNewCounter() }) {
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
                items(allCounterJobs) { ti ->
                    CounterJobItem(
                        onGoToAllCounterJobs = onGoToAllCounterJobs,
                        onGoToEditCounterJob = onGoToEditCounterJob,
                        item = ti,
                        onEndCounterJob = onEndCounterJob,
                        onNavigateToAllCounterInstance = onNavigateToCounterJobInstances,
                        job = ti as CounterJobModel
                    )
                }
            }
        }
    }
}

@Composable
private fun CounterJobItem(
    onGoToAllCounterJobs: (job: IJobModel) -> Unit,
    item: IJobModel,
    onEndCounterJob: (id: Int) -> Unit,
    onNavigateToAllCounterInstance: (idname: String) -> Unit,
    job: CounterJobModel,
    onGoToEditCounterJob: (id: Int) -> Unit,
) {
    Row(
        // verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToAllCounterInstance(job.id.toString() + "/" + job.name) }
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        EditButton(onGoToEditCounterJob, item)
        ShareButton()
        EndButton(item.id!!, onEndCounterJob)
    }
}
@Composable
fun EditButton(onClick: (id: Int) -> Unit, item: IJobModel) {
    Button(onClick = {
        onClick(item.id!!)
    }) {
        Text(text = "Edit")
    }
}
@Composable
fun ShareButton() {
    Button(onClick = {
        //your onclick code here
    }) {
        Text(text = "Share")
    }
}

@Composable
fun EndButton(id: Int, onClick: (id: Int) -> Unit) {
    Button(onClick = {
        onClick(id)
    }) {
        Text(text = "End")
    }
}