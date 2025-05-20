package com.android.chatgpt.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val API_KEY = "AIzaSyB2Y0Biooydq81y8gtJo5Vi2AshzIde60g"

private val client = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 15000
    }
}

@Serializable
data class ContentPart(val text: String)

@Serializable
data class GeminiContent(val parts: List<ContentPart>, val role: String = "user")

@Serializable
data class GeminiRequest(val contents: List<GeminiContent>)

@Serializable
data class GeminiResponse(val candidates: List<GeminiCandidate>)

@Serializable
data class GeminiCandidate(val content: GeminiContent?)

suspend fun fetchGeminiResponse(prompt: String): String {
    return try {
        val request = GeminiRequest(
            contents = listOf(
                GeminiContent(parts = listOf(ContentPart(text = prompt)))
            )
        )

        val response: GeminiResponse = client.post(
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=$API_KEY"
        ) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body() //  Deserializes HttpResponse to GeminiResponse

        response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
            ?: "No response from Gemini."
    } catch (e: Exception) {
        "Error: ${e.localizedMessage}"
    }
}
