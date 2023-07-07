package com.myprojects.growighapp

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("hits")
    val hits: List<PixabayImage>
)
