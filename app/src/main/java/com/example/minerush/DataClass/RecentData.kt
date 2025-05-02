package com.example.minerush.DataClass

data class RecentData(
    val name: String,
    val email: String,
    val phone: String,
    val role: String
)

data class RecentUsersResponse(
    val message: String,
    val status: Int,
    val data: List<RecentData>
)
