package com.sergey.weatherforecast.data.repository

import com.sergey.weatherforecast.network.RetrofitInstance

class WeatherRepository {

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ) = RetrofitInstance.openMeteo.getCurrentWeather(
        latitude,
        longitude
    )
}