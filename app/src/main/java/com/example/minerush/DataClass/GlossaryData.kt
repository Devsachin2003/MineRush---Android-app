package com.example.minerush.DataClass

data class GlossaryData(
    val term: String,
    val definition: String,
    var isExpanded: Boolean = false
)

data class GlossaryResponse(
    val message: String,
    val status: Int,
    val data: List<GlossaryData>
)
