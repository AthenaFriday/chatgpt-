package com.android.chatgpt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chatgpt.repository.GeminiRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SummarizerViewModel : ViewModel() {

    private val repo = GeminiRepositoryImpl()

    private val _summary = MutableStateFlow("")
    val summary: StateFlow<String> = _summary

    fun summarize(text: String) {
        viewModelScope.launch {
            _summary.value = "Summarizing..."
            try {
                val result = repo.summarizeArticle(text)
                _summary.value = result ?: "No summary returned."
            } catch (e: Exception) {
                _summary.value = "Error: ${e.localizedMessage ?: "Something went wrong"}"
            }
        }
    }
}
