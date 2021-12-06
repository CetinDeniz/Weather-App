package com.example.learning.data

import com.squareup.moshi.Json

data class WeatherObject(
    @Json(name = "current") val current: Current
    )

data class Current(
    @Json(name = "sunrise") val sunrise: Int,
    @Json(name = "sunset") val sunset: Int,
    @Json(name = "temp") val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "pressure") val pressure: Int,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "wind_speed") val windSpeed: Double,
    @Json(name = "weather") val weather: List<SubWeatherObject>
)

data class SubWeatherObject(
    @Json(name = "main") val main: String, // Error may popup because there is another "main" key // no error
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)

//data class WeatherObject(
//    @Json(name = "name") val name: String,
//    @Json(name = "weather") val subWeatherObject: List<SubWeatherObject>,  // Error may popup because declared as a list // no error
//    @Json(name = "wind") val wind: Wind,
//    val main: Main
//)

//data class Wind(@Json(name = "speed") val speed: Double)

//data class Main(
//    @Json(name = "temp") val temp: Double,
//    @Json(name = "humidity") val humidity: Int,
//    @Json(name = "feels_like") val feelsLike: Double
//)
//

/**
 * For getting lon,lat in first API call with cityName: String
 */
data class Location(@Json(name = "coord") val coord: Coord)

data class Coord(
    @Json(name = "lon") val lon: Double,
    @Json(name = "lat") val lat: Double
)