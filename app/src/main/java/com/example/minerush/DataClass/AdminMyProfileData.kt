package com.example.minerush.DataClass

data class AdminProfile(
    val email: String,
    val name: String,
    val gender: String,
    val phone: String,
    val responsibility: String,
    val profile_image: String? = null
)

data class AdminProfileResponse(val admin: AdminProfile)