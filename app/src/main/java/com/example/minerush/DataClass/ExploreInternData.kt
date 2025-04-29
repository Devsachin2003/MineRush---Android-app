package com.example.minerush.DataClass

data class ExploreInternData(
    val title: String,
    val company: String,
    val location: String,
    val duration: String,
    val description: String,
    val mode: String,
    var isExpanded: Boolean = false
)

data class InternshipResponse(
    val internships: List<ExploreInternData>
)
