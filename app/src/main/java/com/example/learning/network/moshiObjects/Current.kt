package com.example.learning.network.moshiObjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Current(
    @Json(name = "dt") val currentTime: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @Json(name = "wind_speed") val windSpeed: Double,
    val weather: List<WeatherDescription>,
)