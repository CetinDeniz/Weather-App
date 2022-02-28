package com.example.learning.network.moshiObjects

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDescription(
    val main: String,
    val description: String,
    val icon: String,
)