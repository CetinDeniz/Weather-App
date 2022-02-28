package com.example.learning.database.entity

import androidx.room.TypeConverters
import com.example.learning.database.Converters

class CurrentForecast(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,

    val currentTime: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,

    @TypeConverters(Converters::class)
    val weather: List<WeatherDescriptionForecast>,
)
