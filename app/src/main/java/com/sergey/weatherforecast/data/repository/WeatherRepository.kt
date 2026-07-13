package com.sergey.weatherforecast.data.repository

import com.sergey.weatherforecast.data.local.CityDao
import com.sergey.weatherforecast.data.local.CityEntity
import com.sergey.weatherforecast.data.remote.WeatherApi
import kotlinx.coroutines.flow.Flow

class WeatherRepository(
    private val cityDao: CityDao,
    private val weatherApi: WeatherApi
) {

    fun getCities(): Flow<List<CityEntity>> {
        return cityDao.getAllCities()
    }

    suspend fun addCity(city: CityEntity) {
        cityDao.insert(city)
    }

    suspend fun deleteCity(city: CityEntity) {
        cityDao.delete(city)
    }

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ) = weatherApi.getCurrentWeather(
        latitude = latitude,
        longitude = longitude
    )
}