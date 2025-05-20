package com.android.chatgpt.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.chatgpt.viewmodel.SummarizerViewModel

@Composable
fun SummarizerScreen(viewModel: SummarizerViewModel = viewModel()) {
    var input by remember { mutableStateOf("") }
    val summary by viewModel.summary.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Paste article here") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.summarize(input) }) {
            Text("Summarize")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = summary)
    }
}
