package com.example.harmonic.components.create_new_routine_instance

import androidx.compose.runtime.Composable
import com.example.harmonic.components.run_routine_instance.CreateNewRoutineInstanceScreen

@Composable
fun CreateNewRoutineInstanceRoute(
    instanceIdString: String,
    onGoToHome: () -> Unit,
) {
    CreateNewRoutineInstanceScreen(
        instanceId = instanceIdString.toInt(),
        onGoToHome = { onGoToHome() }
    )
}