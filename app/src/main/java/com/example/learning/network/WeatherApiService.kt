package com.example.learning.network

import com.example.learning.network.moshiObjects.Forecast
import com.example.learning.network.moshiObjects.Location
import com.squareup.moshi.Moshi
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
/** https://api.openweathermap.org/data/2.5/weather?&appid=9e764a977cc73629131ac0c5ac3de4d1 */
private const val BASE_URL = "https://api.openweathermap.org/"
private const val API_KEY = "9e764a977cc73629131ac0c5ac3de4d1"
const val ICON_URL_FIRST = "http://openweathermap.org/img/wn/"
const val ICON_URL_SECOND = "@2x.png"
private const val UNITS = "metric"
const val CITY = "İzmir"

private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
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


    /**
     * exclude=minutely,hourly,daily
     */
    @GET("data/2.5/onecall?&units=$UNITS&exclude=minutely&appid=$API_KEY")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double, @Query("lon") longitude: Double
    ): Forecast
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}
