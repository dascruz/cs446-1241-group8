package com.example.harmonic.components.create_new_counter_instances

import androidx.compose.runtime.Composable

@Composable
fun CreateNewCounterInstanceRoute(
    instanceIdString: String,
    onNavigateToInstanceList: () -> Unit,
) {
    CreateNewCounterInstanceScreen(
        instanceId = instanceIdString.toInt(),
        onNavigateToInstanceList = { onNavigateToInstanceList() }
    )
}