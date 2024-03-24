package com.example.harmonic.components.timer_job_list

// import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
// import com.example.harmonic.components.view_all_active.ActiveInstanceItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harmonic.components.view_all_active.TimerJobListViewModel
import com.example.harmonic.models.IJobModel
import com.example.harmonic.models.jobs.TimerJobModel

@Composable
fun TimerJobListScreen(
    onGoToNewTimer: () -> Unit,
    onNavigateToAllTimerInstance: (idname: String) -> Unit,
    onGoToEditTimerJob: (id: Int) -> Unit,
    viewModel: TimerJobListViewModel = hiltViewModel()
) {
    val allTimerJobs by viewModel.allTimerInstanceFlow.collectAsState(initial = emptyList())
    val sharingJobId by viewModel.sharedJobId.collectAsState()

    TimerJobListScreen(
        allTimerJobs = allTimerJobs,
        onGoToNewTimer = onGoToNewTimer,
        onNavigateToTimerJobInstances = onNavigateToAllTimerInstance,
        onGoToEditTimerJob = onGoToEditTimerJob,
        onShareJob = {job: TimerJobModel -> viewModel.shareJob(job)},
        onDoneShareJob = { viewModel.doneSharingJob() },
        sharingJobId = sharingJobId
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerJobListScreen(
    onGoToNewTimer: () -> Unit,
    onGoToEditTimerJob: (id: Int) -> Unit,
    onNavigateToTimerJobInstances: (idname: String) -> Unit,
    onShareJob: (job: TimerJobModel) -> Unit,
    onDoneShareJob: () -> Unit,
    allTimerJobs: List<IJobModel>,
    sharingJobId: String
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
            FloatingActionButton(onClick = { onGoToNewTimer() }) {
                Icon(Icons.Default.Add, contentDescription = "New")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            val showDialog = remember{ mutableStateOf(false) }
            val title = remember { mutableStateOf("") }
            if (showDialog.value) {
                SharingDialog(
                    onDismiss = {
                        onDoneShareJob()
                        showDialog.value = false
                        title.value = ""
                    },
                    sharingJobId = sharingJobId,
                    title = title.value
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(allTimerJobs) { ti ->
                    TimerJobItem(
                        onGoToEditTimerJob = onGoToEditTimerJob,
                        item = ti,
                        onNavigateToAllTimerInstance = onNavigateToTimerJobInstances, 
                        job = ti as TimerJobModel,
                        onShare = {job: TimerJobModel ->
                            onShareJob(job)
                            title.value = "Sharing Timer Job ${job.name}"
                            showDialog.value = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun TimerJobItem(
    item: IJobModel,
    onNavigateToAllTimerInstance: (idname: String) -> Unit,
    job: TimerJobModel,
    onGoToEditTimerJob: (id: Int) -> Unit,
    onShare: (job: TimerJobModel) -> Unit
) {
    Row(
        // verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToAllTimerInstance(job.id.toString() + "/" + job.name) }
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        EditButton(onGoToEditTimerJob, item)
        ShareButton { onShare(job) }
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
fun ShareButton(onClick: () -> Unit) {
    Button(onClick = {
        onClick()
    }) {
        Text(text = "Share")
    }
}

@Composable
fun SharingDialog(
    onDismiss: () -> Unit,
    sharingJobId: String,
    title: String
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .height(250.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                if (sharingJobId == "") {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                } else {
                    Text(
                        text = sharingJobId,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val clipboardManager: ClipboardManager = LocalClipboardManager.current
                    TextButton(
                        onClick = { clipboardManager.setText(AnnotatedString(sharingJobId)) },
                        modifier = Modifier.padding(8.dp),
                        enabled = sharingJobId != ""
                    ) {
                        Text("Copy to Clipboard")
                    }
                    TextButton(
                        onClick = { onDismiss() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        }
    }
}
