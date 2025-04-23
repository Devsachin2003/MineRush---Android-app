package com.example.minerush.api.serverresponse

import android.provider.ContactsContract.CommonDataKinds.Organization

data class SignUpRequest(
    val name: String,      // Full name of the user
    val email: String, // Email address of the user
    val gender: String,
    val phone: String,
    val organization: String,
    val username: String,
    val role: String,
    val password: String, // Password chosen by the user
    val confirmpassword: String
)

data class SignUpResponse(
    val success: Boolean,     // Indicates if the sign-up was successful
    val message: String,      // Backend message (e.g., "Registration successful")
    val userId: Int? = null,  // Optional field for the user ID if returned
    val token: String? = null // Optional field for token if returned (for auto-login scenarios)
)
