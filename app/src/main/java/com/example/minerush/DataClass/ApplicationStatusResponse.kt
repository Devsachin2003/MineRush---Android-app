package com.example.minerush.DataClass

data class ApprovalResponse(
    val status: String,
    val message: String,
    val gmail_url: String? // Nullable as it might not always be present for error cases
)

data class CancellationResponse(
    val status: String,
    val message: String
)