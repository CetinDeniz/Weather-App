package com.example.learning.database.entity

import android.os.Parcelable
import androidx.room.TypeConverters
import com.example.learning.database.Converters
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
class DailyForecast(
    val currentTime: Int,

    @TypeConverters(Converters::class)
    val dailyTemp: @RawValue DailyTempForecast,

    @TypeConverters(Converters::class)
    val feelsLike: @RawValue DailyFeelsLikeForecast,

    @TypeConverters(Converters::class)
    val weatherDescription: @RawValue List<WeatherDescriptionForecast>,
): Weather(),Parcelable