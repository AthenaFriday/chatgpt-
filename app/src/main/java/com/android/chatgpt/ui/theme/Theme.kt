package com.android.chatgpt.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF5A4FCF),
    onPrimary = Color.White,
    background = Color(0xFFF5F5F5),
    onBackground = Color.Black
)

@Composable
fun ChatGptTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(),
        content = content
    )
}
