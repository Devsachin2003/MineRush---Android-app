package com.example.minerush.DataClass

data class InternApplicantDetails(
    val name: String,
    val phone: String,
    val email: String,
    val degreeBranch: String,
    val yearOfStudy: String,
    val skills: String?,
    val resume: String?
)

data class InternApplicantDetailsResponse(
    val message: String,
    val status: Int,
    val data: InternApplicantDetails?
)
