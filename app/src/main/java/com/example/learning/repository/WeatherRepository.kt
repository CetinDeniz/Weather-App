package com.example.learning.repository

import android.util.Log
import com.example.learning.database.WeatherDatabase
import com.example.learning.database.entity.*
import com.example.learning.network.WeatherApi
import com.example.learning.network.moshiObjects.*

class WeatherRepository(val database: WeatherDatabase) {

    suspend fun getWeatherForecast(locationName: String): ForecastDatabase { // ForecastDatabase {
        Log.e("WeatherRepository", "Before network call")
        val weatherData: Forecast = getNetworkData(locationName)
        Log.e("WeatherRepository", "After network call")

        return weatherData.toDatabaseForecast(locationName)
    }

    /**
     * Make weather data request from OpenWeatherMap
     */
    private suspend fun getNetworkData(locationName: String): Forecast {
        val coordinate = WeatherApi.retrofitService.getLocationData(locationName).coord
        Log.e("WeatherRepository",
            "Latitude : " + coordinate.lat.toString() + ", Longitude : " + coordinate.lon.toString())

        /* weatherData: Forecast */
        return WeatherApi.retrofitService.getWeatherData(coordinate.lat, coordinate.lon)
    }

    /**
     * Convert Forecast to ForecastDatabase
     */
    private fun Forecast.toDatabaseForecast(locationName: String): ForecastDatabase {
        return ForecastDatabase(
            locationName = locationName,
            current = current.toCurrentForecast(),
            daily = daily.toDailyForecast(),
            hourly = hourly.toHourlyForecast()
        )
    }


}

private fun Current.toCurrentForecast(): CurrentForecast {
    return CurrentForecast(
        currentTime, sunrise, sunset, temp, feelsLike, pressure, humidity, windSpeed,
        weather.toWeatherDescriptionForecast()
    )
}

private fun List<Hourly>.toHourlyForecast(): List<HourlyForecast> {
    return map {
        HourlyForecast(
            it.currentTime,
            it.hourlyTemp,
            it.windSpeed,
            it.windDegree,
            it.weatherDescription.toWeatherDescriptionForecast()
        )
    }
}

private fun List<Daily>.toDailyForecast(): List<DailyForecast> {
    return map {
        DailyForecast(
            it.currentTime,
            it.dailyTemp.toDailyTempForecast(),
            it.feelsLike.toDailyFeelsLikeForecast(),
            it.weatherDescription.toWeatherDescriptionForecast()
        )
    }
}

private fun List<WeatherDescription>.toWeatherDescriptionForecast(): List<WeatherDescriptionForecast> {
    return map {
        WeatherDescriptionForecast(
            it.main,
            it.description,
            it.icon
        )
    }
}

private fun DailyFeelsLike.toDailyFeelsLikeForecast(): DailyFeelsLikeForecast {
    return DailyFeelsLikeForecast(
        day, night, eve, morn
    )
}

private fun DailyTemp.toDailyTempForecast(): DailyTempForecast {
    return DailyTempForecast(
        day, min, max, night, eve, morn
    )
}