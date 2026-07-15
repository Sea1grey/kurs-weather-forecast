package com.sergey.weatherforecast.data.repository

import com.sergey.weatherforecast.data.local.CityDao
import com.sergey.weatherforecast.data.local.CityEntity
import com.sergey.weatherforecast.data.remote.WeatherApi
import com.sergey.weatherforecast.data.remote.CityApi
import com.sergey.weatherforecast.data.remote.CityResponse
import kotlinx.coroutines.flow.Flow
import com.sergey.weatherforecast.data.preferences.DataStoreManager


class WeatherRepository(
    private val cityDao: CityDao,
    private val weatherApi: WeatherApi,
    private val cityApi: CityApi,
    private val dataStoreManager: DataStoreManager
) {

    fun getCities(): Flow<List<CityEntity>> {
        return cityDao.getAllCities()
    }

    suspend fun addCity(city: CityEntity) {
        cityDao.insert(city)
    }

    suspend fun getCityByName(name: String): CityEntity? {
        return cityDao.getCityByName(name)
    }

    suspend fun cityExists(name: String): Boolean {
        return cityDao.getCityByName(name) != null
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

    suspend fun searchCity(name: String): CityResponse? {
        return cityApi.searchCity(name).firstOrNull()
    }

    suspend fun saveLastCity(city: String) {
        dataStoreManager.saveLastCity(city)
    }

    fun getLastCity() =
        dataStoreManager.lastCity

}