package com.example.harmonic.components.view_all_active

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harmonic.models.IJobInstanceModel
import java.util.UUID

@Composable
fun ViewAllActiveScreen(
    onNavigateToActiveInstance: (id: UUID) -> Unit,
    viewModel: ViewAllActiveViewModel = hiltViewModel()
) {
    val activeInstances by viewModel.allActiveTimerInstanceFlow.collectAsState(initial = emptyList())
    ViewAllActiveScreen(
        onNavigateToActiveInstance = onNavigateToActiveInstance,
        activeInstances = activeInstances
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAllActiveScreen(
    onNavigateToActiveInstance: (id: UUID) -> Unit,
    activeInstances: List<IJobInstanceModel>
) {
    Scaffold(
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
    ) {
    }
}