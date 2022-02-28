package com.example.learning.network.moshiObjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Hourly
 */
@JsonClass(generateAdapter = true)
data class Hourly(
    @Json(name = "dt") val currentTime: Int,
    @Json(name = "temp") val hourlyTemp: Double,
    @Json(name = "wind_speed") val windSpeed: Double,
    @Json(name = "wind_deg") val windDegree: Int,
    @Json(name = "weather") val weatherDescription: List<WeatherDescription>,
)

/**
 * Daily forecast including today and 7 days after (Total 8 days)
 */
@JsonClass(generateAdapter = true)
data class Daily(
    @Json(name = "dt") val currentTime: Int,
    @Json(name = "temp") val dailyTemp: DailyTemp,
    @Json(name = "feels_like") val feelsLike: DailyFeelsLike,
    @Json(name = "weather") val weatherDescription: List<WeatherDescription>,
)

/**
 * Daily forecast details
 */
@JsonClass(generateAdapter = true)
data class DailyTemp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double,
)

@JsonClass(generateAdapter = true)
data class DailyFeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double,
)