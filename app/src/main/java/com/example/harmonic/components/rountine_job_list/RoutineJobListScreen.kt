package com.example.harmonic.components.rountine_job_list

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
import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.jobs.RoutineJobModel

@Composable
fun RoutineJobListScreen(
    onGoToNewRoutine: () -> Unit,
    onNavigateToAllRoutineInstance: (idname: String) -> Unit,
    onGoToEditRoutineJob: (id: Int) -> Unit,
    viewModel: RoutineJobListViewModel = hiltViewModel()
) {
    val allRoutineJobs by viewModel.allRoutineInstanceFlow.collectAsState(initial = emptyList())
    // Navigation if needed
    val onGoToAllRoutineJobs = { job: IJobModel ->
        when(job) {
            is RoutineJobModel -> onNavigateToAllRoutineInstance(job.id.toString() + "/" + job.name)
            else -> {}
        }
    }
    RoutineJobListScreen(
        onGoToAllRoutineJobs = onGoToAllRoutineJobs,
        allRoutineJobs = allRoutineJobs,
        onGoToNewRoutine = onGoToNewRoutine,
        onNavigateToRoutineJobInstances = onNavigateToAllRoutineInstance,
        onGoToEditRoutineJob = onGoToEditRoutineJob,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineJobListScreen(

    onGoToNewRoutine: () -> Unit,
    onGoToEditRoutineJob: (id: Int) -> Unit,
    onGoToAllRoutineJobs: (job: IJobModel) -> Unit,
    onNavigateToRoutineJobInstances: (idname: String) -> Unit,
    allRoutineJobs: List<IJobModel>
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
                    Text("Your Routines")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onGoToNewRoutine() }) {
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
                items(allRoutineJobs) { ti ->
                    RoutineJobItem(
                        onGoToAllRoutineJobs = onGoToAllRoutineJobs,
                        onGoToEditRoutineJob = onGoToEditRoutineJob,
                        item = ti,
                        onNavigateToAllRoutineInstance = onNavigateToRoutineJobInstances,
                        job = ti as RoutineJobModel
                    )
                }
            }
        }
    }
}

@Composable
private fun RoutineJobItem(
    onGoToAllRoutineJobs: (job: IJobModel) -> Unit,
    item: IJobModel,

    onNavigateToAllRoutineInstance: (idname: String) -> Unit,
    job: RoutineJobModel,
    onGoToEditRoutineJob: (id: Int) -> Unit,
) {
    Row(
        // verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToAllRoutineInstance(job.id.toString() + "/" + job.name) }
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        EditButton(onGoToEditRoutineJob, item)
        ShareButton()
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
