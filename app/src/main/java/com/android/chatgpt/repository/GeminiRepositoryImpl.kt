package com.android.chatgpt.repository

import com.google.firebase.Firebase
import com.google.firebase.app
import com.google.firebase.vertexai.FirebaseVertexAI
import com.google.firebase.vertexai.GenerativeModel
import com.google.firebase.vertexai.type.HarmBlockThreshold
import com.google.firebase.vertexai.type.HarmCategory
import com.google.firebase.vertexai.type.SafetySetting
import com.google.firebase.vertexai.type.generationConfig

class GeminiRepositoryImpl {

    private val generativeModel: GenerativeModel by lazy {
        val app = Firebase.app // Gets the default FirebaseApp

        FirebaseVertexAI.getInstance(app).generativeModel(
            modelName = "gemini-2.0-flash",
            generationConfig = generationConfig {
                temperature = 0f
            },
            safetySettings = listOf(
                SafetySetting(HarmCategory.HARASSMENT, HarmBlockThreshold.LOW_AND_ABOVE),
                SafetySetting(HarmCategory.HATE_SPEECH, HarmBlockThreshold.LOW_AND_ABOVE),
                SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, HarmBlockThreshold.LOW_AND_ABOVE),
                SafetySetting(HarmCategory.DANGEROUS_CONTENT, HarmBlockThreshold.LOW_AND_ABOVE)
            )
        )
    }

    // suspend function, assuming generateContent is suspend too
    suspend fun summarizeArticle(articleText: String): String {
        val prompt = """
            Summarize the core findings of the following article in 4 concise bullet points. 
            Ensure each bullet point is specific, informative and relevant. 
            Return just the bullet points as plain text. Don't use markdown.

            $articleText
        """.trimIndent()

        val response = generativeModel.generateContent(prompt) // ✅ No .await() if this is already suspend
        return response.text ?: "No response."
    }
}
