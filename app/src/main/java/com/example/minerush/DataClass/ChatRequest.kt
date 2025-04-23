package com.example.minerush.DataClass

data class ChatRequest(
    val message: String,
    val history: List<History>
)
