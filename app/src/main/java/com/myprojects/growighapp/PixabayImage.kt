package com.myprojects.growighapp

import com.google.gson.annotations.SerializedName

data class PixabayImage (
    @SerializedName("webformatURL")
    val imageUrl: String
)