package com.android.chatgpt.repository

import com.google.firebase.Firebase
import com.google.firebase.vertexai.type.HarmBlockThreshold
import com.google.firebase.vertexai.type.HarmCategory
import com.google.firebase.vertexai.type.SafetySetting
import com.google.firebase.vertexai.type.generationConfig
import com.google.firebase.vertexai.vertexAI

class GeminiRepositoryImpl {

    private val generativeModel = Firebase.vertexAI.generativeModel(
        modelName = "gemini-2.0-flash",
        generationConfig = generationConfig {
            temperature = 0f
        },
        safetySettings = listOf(
            SafetySetting(HarmCategory.HARASSMENT, HarmBlockThreshold.LOW_AND_ABOVE),
            SafetySetting(HarmCategory.HATE_SPEECH, HarmBlockThreshold.LOW_AND_ABOVE),
            SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, HarmBlockThreshold.LOW_AND_ABOVE),
            SafetySetting(HarmCategory.DANGEROUS_CONTENT, HarmBlockThreshold.LOW_AND_ABOVE),
        )
    )

    suspend fun summarizeArticle(articleText: String): String? {
        val prompt =
            "Summarize the following article in 4 concise bullet points. " +
                    "Ensure each bullet point is specific, informative and relevant. " +
                    "Return just the bullet points as plain text. " +
                    "Use plain text, don't use markdown. \n $articleText"

        return generativeModel.generateContent(prompt).text

    }

}