package com.example.learning.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learning.database.WeatherDatabase
import com.example.learning.database.entity.ForecastDatabase
import com.example.learning.network.CITY
import com.example.learning.repository.WeatherRepository
import kotlinx.coroutines.launch

//enum class Status {
//    LOADING, DONE, ERROR
//}

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    val database = WeatherDatabase.getDatabase(application)
    private val weatherRepository = WeatherRepository(database)

    private var _weather = MutableLiveData<ForecastDatabase>()
    val weather: LiveData<ForecastDatabase> = _weather

//    private val _location = MutableLiveData<Location>()

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

//    private val _status = MutableLiveData<Status>()
//    val status: LiveData<Status> = _status

    init {
        Log.e("ViewModel", "init")
        getData(CITY) /* CITY */
//        Log.e("ViewModel", _cityName.value ?: "It's null")
    }

    private fun getData(city: String = CITY) {
        viewModelScope.launch {

//            _status.value = Status.LOADING
            Log.e("getData", "Loading")
            try {
//                Log.e("getData", "Location will be get")
//                _location.value = WeatherApi.retrofitService.getLocationData(city) // lat,lon
//                Log.e("getData", "Location taken")
//
//                val latitude = _location.value!!.coord.lat
//                val longitude = _location.value!!.coord.lon
//                Log.e("getData", "Latitude $latitude and Longitude $longitude")
                _weather.value =
                    weatherRepository.getWeatherForecast(city)// WeatherApi.retrofitService.getWeatherData(latitude, longitude)
                _cityName.value = city
//                    _status.value = Status.DONE
                Log.e("getData", "Done")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("getData", "getWeatherData problem")
            }
        }
    }

    fun makeCall(city: String) {
        Log.e("makeCall argument: ", city)
        getData(city)
    }

    fun addFavorites() {
//        _weather.value?.isFavorite = 1
        viewModelScope.launch {
            weatherRepository.database.weatherDao().addToFavorites(_weather.value!!)
        }
    }

    fun removeFromFavorites() {
//        _weather.value?.isFavorite = 0
        viewModelScope.launch {
            weatherRepository.database.weatherDao()
                .deleteFromFavorites(_weather.value!!.locationName)
        }
    }


}