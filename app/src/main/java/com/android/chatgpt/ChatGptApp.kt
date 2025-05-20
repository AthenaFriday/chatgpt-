package com.android.chatgpt

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.chatgpt.ui.SummarizerScreen
import com.android.chatgpt.ui.theme.ChatGptTheme

@Composable
fun ChatGptApp() {
    ChatGptTheme {
        Surface(modifier = Modifier) {
            SummarizerScreen()
        }
    }
}
