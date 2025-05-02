package com.example.minerush.DataClass

data class InternApplicantResponse(
    val message: String,
    val status: Int,
    val data: List<InternApplicant>
)
