package com.sergey.weatherforecast.network

import com.sergey.weatherforecast.data.remote.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoService {

    @GET("v1/forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String =
            "temperature_2m,wind_speed_10m"
    ): WeatherResponse
}