package com.example.harmonic.components.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.harmonic.ui.theme.HarmonicTheme


@Composable
fun HomeScreen(
    onGoToTracking: () -> Unit,
    onGoToAllActive: () -> Unit,
    onGoToInsights: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Harmonic",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 72.dp).fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
        )
        Column(
            modifier = Modifier.padding(top = 72.dp, bottom = 48.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onGoToTracking() },
                modifier = Modifier.size(width = 240.dp, height = 48.dp)
            ) {
                Text(
                    text = "Start Tracking",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Button(
                onClick = { onGoToAllActive() },
                modifier = Modifier.size(width = 240.dp, height = 72.dp).padding(top = 24.dp)
            ) {
                Text(
                    text = "View All Active Jobs",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Button(
                onClick = { onGoToInsights() },
                modifier = Modifier.size(width = 240.dp, height = 72.dp).padding(top = 24.dp)
            ) {
                Text(
                    text = "See Insights",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@Preview(name = "Home light theme", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Home dark theme", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {
    HarmonicTheme {
        HomeScreen(
            onGoToTracking = {},
            onGoToAllActive = {},
            onGoToInsights = {}
        )
    }
}