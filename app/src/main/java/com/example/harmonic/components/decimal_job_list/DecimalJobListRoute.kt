package com.example.harmonic.components.decimal_job_list

import androidx.compose.runtime.Composable

@Composable
fun DecimalJobListRoute(
        onGoToNewDecimal: () -> Unit,
        onGoToEditDecimalJob: (id: Int) -> Unit,
        onNavigateToAllDecimalInstance: (idname: String) -> Unit
) {
    DecimalJobListScreen(
            onGoToNewDecimal = { onGoToNewDecimal() },
            onNavigateToAllDecimalInstance = { idname: String ->
                onNavigateToAllDecimalInstance(idname)
            },
            onGoToEditDecimalJob = { id: Int -> onGoToEditDecimalJob(id) },
    )
}
