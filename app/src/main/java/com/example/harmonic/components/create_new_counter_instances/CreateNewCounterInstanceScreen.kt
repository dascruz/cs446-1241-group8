package com.example.harmonic.components.create_new_counter_instances
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.fillMaxSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewCounterInstanceScreen(
    viewModel: CreateNewCounterInstanceViewModel = hiltViewModel(),
    instanceId: Int,
    onNavigateToInstanceList: () -> Unit
) {
    val composableScope = rememberCoroutineScope()

    val count = remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

        Text(text = "Enter Counter Value", textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp))

        IntegerInput(
            value = count.value,
            onValueChange = { newValue ->
                count.value = newValue
            }
        )


        Button(onClick = {
            composableScope.launch {
                viewModel.setCount(count.value)
                onNavigateToInstanceList()
            }
        }) {
            Icon(Icons.Default.Add, contentDescription = "New")
        }
    }
}
@Composable
fun IntegerInput(
    value: Int,
    onValueChange: (Int) -> Unit
) {
    TextField(
        value = value.toString(),
        onValueChange = { newValue ->
            onValueChange(newValue.toIntOrNull() ?: 0)
        },
        label = { },
        modifier = Modifier.padding(16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { /* Perform action when done button is pressed */ })


    )
}