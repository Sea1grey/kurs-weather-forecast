package com.sergey.weatherforecast.data.remote

data class WeatherResponse(
    val current: CurrentWeather
)

data class CurrentWeather(
    val temperature_2m: Double,
    val wind_speed_10m: Double
)