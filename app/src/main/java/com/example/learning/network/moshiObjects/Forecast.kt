package com.example.learning.network.moshiObjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "current") val current: Current,
    @Json(name = "daily") val daily: List<Daily>,
    @Json(name = "hourly") val hourly: List<Hourly>,
)