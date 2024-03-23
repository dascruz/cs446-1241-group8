package com.example.harmonic.components.create_new_routine_instance

import androidx.compose.runtime.Composable

@Composable
fun CreateNewRoutineInstanceRoute(
    instanceIdString: String,
    onNavigateToInstanceList: () -> Unit,
) {
    CreateNewRoutineInstanceScreen(
        instanceId = instanceIdString.toInt(),
        onNavigateToInstanceList = { onNavigateToInstanceList() }
    )
}