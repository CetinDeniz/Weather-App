package com.example.learning.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learning.database.entity.ForecastDatabase

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(forecast: ForecastDatabase)

    @Query("SELECT * FROM forecastdatabase WHERE isFavorite == 1")
    fun getFavorites(): LiveData<List<ForecastDatabase>>

    @Query("DELETE FROM forecastdatabase WHERE locationName == :forecastLocationName")
    suspend fun deleteFromFavorites(forecastLocationName: String)

//    @Delete()
//    suspend fun deleteFromFavorites(forecast: ForecastDatabase)
}