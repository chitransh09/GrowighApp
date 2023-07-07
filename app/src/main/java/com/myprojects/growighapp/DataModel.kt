package com.myprojects.growighapp

data class DataModel(
    val picItems: List<ApiResponse>,
    val total: Int,
    val totalHits: Int
)