package com.android.lawbot.DataClasses

data class ChatRequest(
    val message: String,
    val history: List<HistoryItem>
)

data class HistoryItem(
    val user: String?,
    val bot: String?
)

data class ChatResponse(
    val message: String,
    val history: List<HistoryItem>
)
