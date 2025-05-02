package com.example.minerush.DataClass

import com.google.gson.annotations.SerializedName

data class ManageData(
    @SerializedName("usernameTV") val nameTV: String,
    @SerializedName("roleTV") val roleTV: String,
    @SerializedName("emailTV") val emailTV: String,
    @SerializedName("phoneTV") val phoneTV: String
)

data class ManageUsersResponse(
    val message: String,
    val status: Int,
    val data: List<ManageData>
)