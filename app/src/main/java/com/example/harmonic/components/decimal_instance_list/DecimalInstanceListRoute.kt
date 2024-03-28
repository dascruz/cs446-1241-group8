package com.example.harmonic.components.decimal_instance_list

import androidx.compose.runtime.Composable

@Composable
fun DecimalInstanceListRoute(
                jobIdString: String,
                jobName: String,
                onNavigateToNewDecimalInstance: (id: Int) -> Unit
) {
        DecimalInstanceListScreen(
                        jobId = jobIdString.toInt(),
                        jobName = jobName,
                        onNavigateToNewDecimalInstance = { id: Int ->
                                onNavigateToNewDecimalInstance(id)
                        }
        )
}
