package com.example.learning.data

import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learning.network.CITY
import com.example.learning.network.WeatherApi
import kotlinx.coroutines.launch

enum class Status {
    LOADING, DONE, ERROR
}

class WeatherViewModel : ViewModel() {

    private var _weather = MutableLiveData<WeatherObject>()
    val weather: LiveData<WeatherObject> = _weather

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    init {
        Log.e("ViewModel", "init")
        getData(CITY) /* CITY */
        Log.e("ViewModel", _cityName.value ?: "It's null")
    }

    private fun getData(city: String = CITY) {  // city: String = CITY
        viewModelScope.launch {

            _status.value = Status.LOADING
            Log.e("getData", "Loading")

            try {
                Log.e("getData", "Location will be get")
                _location.value = WeatherApi.retrofitService.getLocationData(city) // lat,lon
                Log.e("getData", "Location taken")

                val latitude = _location.value!!.coord.lat
                val longitude = _location.value!!.coord.lon
                Log.e("getData", "Latitude $latitude and Longitude $longitude")

                try {
                    _weather.value = WeatherApi.retrofitService.getWeatherData(latitude, longitude)
                    _cityName.value = city
                    _status.value = Status.DONE
                    Log.e("getData", "Done")

                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("getData", "getWeatherData problem")
                }

            } catch (e: Exception) {
                _status.value = Status.ERROR
                Log.e("getLocationData", "Error")
            }
        }
    }

    fun makeCall(city: String) {
        Log.e("makeCall argument: ", city)
        getData(city)
    }


}