package com.example.learning.network

import androidx.lifecycle.MutableLiveData
import com.example.learning.data.Location
import com.example.learning.data.WeatherObject
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * q={city name},{state code},{country code}
 * units=metric
 *
 * id={city id}           -> By city ID
 * lat={lat}&lon={lon}    -> By geographic coordinates
 * zip={zip code},{country code} -> By zip code
 *
 * appid=9e764a977cc73629131ac0c5ac3de4d1
 */

private const val BASE_URL = "https://api.openweathermap.org/"
private const val API_KEY = "9e764a977cc73629131ac0c5ac3de4d1"
var ICON_URL = "http://openweathermap.org/img/wn/{iconCode}@2x.png"
private const val UNITS = "metric"
const val CITY = "London,uk"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface WeatherApiService {
    @GET("data/2.5/weather?&appid=$API_KEY") // q=cityName , &units=$UNITS
    suspend fun getLocationData(
        @Query("q") cityName: String
    ): Location  /* For getting lat,lon*/

    @GET("data/2.5/onecall?&units=metric&exclude=minutely,hourly,daily&appid=$API_KEY")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double, @Query("lon") longitude: Double
    ): WeatherObject
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}
