package com.example.learning.network.moshiObjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * For getting lon,lat in first API call with "cityName: String"
 */
@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "coord") val coord: Coord,
)

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lon") val lon: Double,
    @Json(name = "lat") val lat: Double,
)