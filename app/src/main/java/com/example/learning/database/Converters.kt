package com.example.learning.database

import android.text.TextUtils
import androidx.room.TypeConverter
import com.example.learning.database.entity.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

    /**
     * Current Forecast
     */
    @TypeConverter
    fun toCurrentForecast(value: String): CurrentForecast? {
        if (TextUtils.isEmpty(value))
            return null

        val jsonAdapter = moshi.adapter(CurrentForecast::class.java)
        return jsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromCurrentForecast(user: CurrentForecast): String {
        val jsonAdapter = moshi.adapter(CurrentForecast::class.java)
        return jsonAdapter.toJson(user)
    }

    /**
     * List of Daily Forecast
     */
    @TypeConverter
    fun toDailyForecast(value: String): List<DailyForecast>? {
        if (TextUtils.isEmpty(value))
            return null

        val type = Types.newParameterizedType(String::class.java)
        val jsonAdapter = moshi.adapter<List<DailyForecast>>(type)

        return jsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromDailyForecast(dailyForecastList: List<DailyForecast>): String {
        val type = Types.newParameterizedType(List::class.java, DailyForecast::class.java)
        val jsonAdapter = moshi.adapter<List<DailyForecast>>(type)

        return jsonAdapter.toJson(dailyForecastList)
    }

    /**
     * List of Hourly Forecast
     */
    @TypeConverter
    fun toHourlyForecast(value: String): List<HourlyForecast>? {
        if (TextUtils.isEmpty(value))
            return null

        val type = Types.newParameterizedType(String::class.java)
        val jsonAdapter = moshi.adapter<List<HourlyForecast>>(type)

        return jsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromHourlyForecast(dailyForecastList: List<HourlyForecast>): String {
        val type = Types.newParameterizedType(List::class.java, HourlyForecast::class.java)
        val jsonAdapter = moshi.adapter<List<HourlyForecast>>(type)

        return jsonAdapter.toJson(dailyForecastList)
    }

    /**
     * List of WeatherDescriptionForecast
     */
    @TypeConverter
    fun toWeatherDescriptionForecast(value: String): List<WeatherDescriptionForecast>? {
        if (TextUtils.isEmpty(value))
            return null

        val type = Types.newParameterizedType(String::class.java)
        val jsonAdapter = moshi.adapter<List<WeatherDescriptionForecast>>(type)

        return jsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromWeatherDescriptionForecast(weatherDescriptionForecast: List<WeatherDescriptionForecast>): String {
        val type =
            Types.newParameterizedType(List::class.java, WeatherDescriptionForecast::class.java)
        val jsonAdapter = moshi.adapter<List<WeatherDescriptionForecast>>(type)

        return jsonAdapter.toJson(weatherDescriptionForecast)
    }

    /**
     * DailyTempForecast
     */
    @TypeConverter
    fun toDailyTempForecast(value: String): DailyTempForecast? {
        if (TextUtils.isEmpty(value))
            return null

        val jsonAdapter = moshi.adapter(DailyTempForecast::class.java)
        return jsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromDailyTempForecast(dailyTempForecast: DailyTempForecast): String {
        val jsonAdapter = moshi.adapter(DailyTempForecast::class.java)
        return jsonAdapter.toJson(dailyTempForecast)
    }

    /**
     * DailyFeelsLikeTempForecast
     */
    @TypeConverter
    fun toDailyFeelsLikeTempForecast(value: String): DailyFeelsLikeForecast? {
        if (TextUtils.isEmpty(value))
            return null

        val jsonAdapter = moshi.adapter(DailyFeelsLikeForecast::class.java)
        return jsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromDailyFeelsLikeTempForecast(dailyTempForecast: DailyFeelsLikeForecast): String {
        val jsonAdapter = moshi.adapter(DailyFeelsLikeForecast::class.java)
        return jsonAdapter.toJson(dailyTempForecast)
    }

}