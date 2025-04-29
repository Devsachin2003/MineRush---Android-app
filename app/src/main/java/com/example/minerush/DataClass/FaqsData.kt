package com.example.minerush.DataClass

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class FaqsData(
    val question: String,
    val answer: String,
    var isExpanded:Boolean
)

data class FaqsResponse(
    val message: String,
    val status: Int,
    val data: DataWrapper
)

data class DataWrapper(
    @SerializedName("About the chatbot")
    val basics: List<FaqsData> = emptyList(),
    @SerializedName("Using and supporting the chatbot")
    val usage: List<FaqsData> = emptyList(),
    @SerializedName("Stakeholders and customers")
    val stakeholders: List<FaqsData> = emptyList()
)

