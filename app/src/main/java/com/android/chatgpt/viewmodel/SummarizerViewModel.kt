package com.android.chatgpt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chatgpt.repository.GeminiRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SummarizerViewModel : ViewModel() {

    // Initialize the Gemini repository without parameters
    private val repo = GeminiRepositoryImpl()

    private val _summary = MutableStateFlow("")
    val summary: StateFlow<String> = _summary

    fun summarize(text: String) {
        viewModelScope.launch {
            _summary.value = "Summarizing..."
            _summary.value = repo.summarizeArticle(text)
        }
    }
}
