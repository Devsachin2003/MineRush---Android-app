package com.example.minerush.DataClass

data class AdminProfile(
    val email: String,
    val name: String,
    val gender: String,
    val phone: String,
    val responsibility: String
)

data class AdminProfileResponse(val admin: AdminProfile)