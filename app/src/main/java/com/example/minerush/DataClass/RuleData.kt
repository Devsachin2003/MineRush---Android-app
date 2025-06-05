package com.example.minerush.DataClass

data class RulesResponse(
    val data: RulesData,
    val message: String,
    val status: Int
)

data class RulesData(
    val rulename: String,
    val description: String,
    val document: String,
    val image: String,
    val video: String
)
