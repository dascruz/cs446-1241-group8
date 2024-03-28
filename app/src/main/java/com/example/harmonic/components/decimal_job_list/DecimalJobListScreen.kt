package com.example.harmonic.components.decimal_job_list

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
import com.example.harmonic.components.view_all_active.DecimalJobListViewModel
import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.jobs.DecimalJobModel

@Composable
fun DecimalJobListScreen(
        onGoToNewDecimal: () -> Unit,
        onNavigateToAllDecimalInstance: (idname: String) -> Unit,
        onGoToEditDecimalJob: (id: Int) -> Unit,
        viewModel: DecimalJobListViewModel = hiltViewModel()
) {
    val allDecimalJobs by viewModel.allDecimalInstanceFlow.collectAsState(initial = emptyList())
    val onGoToAllDecimalJobs = { job: IJobModel ->
        when (job) {
            is DecimalJobModel -> onNavigateToAllDecimalInstance(job.id.toString() + "/" + job.name)
            else -> {}
        }
    }
    DecimalJobListScreen(
            onGoToAllDecimalJobs = onGoToAllDecimalJobs,
            allDecimalJobs = allDecimalJobs,
            onGoToNewDecimal = onGoToNewDecimal,
            onNavigateToDecimalJobInstances = onNavigateToAllDecimalInstance,
            onGoToEditDecimalJob = onGoToEditDecimalJob,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DecimalJobListScreen(
        onGoToNewDecimal: () -> Unit,
        onGoToEditDecimalJob: (id: Int) -> Unit,
        onGoToAllDecimalJobs: (job: IJobModel) -> Unit,
        onNavigateToDecimalJobInstances: (idname: String) -> Unit,
        allDecimalJobs: List<IJobModel>
) {
    Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                        colors =
                                TopAppBarDefaults.topAppBarColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                                        titleContentColor = MaterialTheme.colorScheme.primary
                                ),
                        title = { Text("Your Decimals") }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { onGoToNewDecimal() }) {
                    Icon(Icons.Default.Add, contentDescription = "New")
                }
            }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(allDecimalJobs) { ti ->
                    DecimalJobItem(
                            onGoToAllDecimalJobs = onGoToAllDecimalJobs,
                            onGoToEditDecimalJob = onGoToEditDecimalJob,
                            item = ti,
                            onNavigateToAllDecimalInstance = onNavigateToDecimalJobInstances,
                            job = ti as DecimalJobModel
                    )
                }
            }
        }
    }
}

@Composable
private fun DecimalJobItem(
        onGoToAllDecimalJobs: (job: IJobModel) -> Unit,
        item: IJobModel,
        onNavigateToAllDecimalInstance: (idname: String) -> Unit,
        job: DecimalJobModel,
        onGoToEditDecimalJob: (id: Int) -> Unit,
) {
    Row(
            modifier =
                    Modifier.fillMaxWidth().clickable {
                        onNavigateToAllDecimalInstance(job.id.toString() + "/" + job.name)
                    }
    ) {
        Text(
                text = item.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp).weight(1f)
        )
        EditButton(onGoToEditDecimalJob, item)
        ShareButton()
    }
}

@Composable
fun EditButton(onClick: (id: Int) -> Unit, item: IJobModel) {
    Button(onClick = { onClick(item.id!!) }) { Text(text = "Edit") }
}

@Composable
fun ShareButton() {
    Button(onClick = {}) { Text(text = "Share") }
}
