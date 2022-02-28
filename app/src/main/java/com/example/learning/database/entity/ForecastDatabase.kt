package com.example.learning.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.learning.database.Converters

@Entity(indices = [Index(value = ["locationName"], unique = true)])
data class ForecastDatabase(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var locationName: String,
//    var isFavorite: Int = 0,

    @TypeConverters(Converters::class)
    val current: CurrentForecast,

    @TypeConverters(Converters::class)
    val daily: List<DailyForecast>,

    @TypeConverters(Converters::class)
    val hourly: List<HourlyForecast>,
)