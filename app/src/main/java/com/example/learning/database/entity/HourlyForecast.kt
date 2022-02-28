package com.example.learning.database.entity

import androidx.room.TypeConverters
import com.example.learning.database.Converters

class HourlyForecast(
    val currentTime: Int,
    val hourlyTemp: Double,
    val windSpeed: Double,
    val windDegree: Int,

    @TypeConverters(Converters::class)
    val weatherDescription: List<WeatherDescriptionForecast>,
): Weather()