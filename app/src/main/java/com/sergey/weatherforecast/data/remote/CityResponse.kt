package com.sergey.weatherforecast.data.remote

data class CityResponse(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String
)