package com.example.minerush.DataClass

import com.google.gson.annotations.SerializedName

data class InternApplicant(
    val name: String,
    val phone: String,
    val email: String,
    @SerializedName("degree_branch") val degree: String,
    @SerializedName("year_of_study") val year: String
)

