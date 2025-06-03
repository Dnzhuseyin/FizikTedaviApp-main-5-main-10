package com.example.fiziktedaviapp.model

// Egzersiz veri modeli
data class Exercise(
    val id: String,
    val name: String,
    val bodyPart: String,
    val duration: String,
    val description: String = "",
    val repetitions: String = "",
    val videoUrl: String? = null,
    val instructions: List<String> = emptyList(),
    val safetyTips: String = "",
    val imageUrl: String? = null
) 