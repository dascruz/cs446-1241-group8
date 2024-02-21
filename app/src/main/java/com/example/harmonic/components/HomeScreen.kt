package com.example.harmonic.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.harmonic.ui.theme.HarmonicTheme


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Harmonic",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(24.dp).fillMaxWidth()
        )
    }
}

@Preview(name = "Home light theme", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Home dark theme", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {
    HarmonicTheme {
        HomeScreen()
    }
}