package com.sergey.weatherforecast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergey.weatherforecast.data.remote.WeatherResponse
import com.sergey.weatherforecast.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    private val _weather =
        MutableStateFlow<WeatherResponse?>(null)

    val weather: StateFlow<WeatherResponse?> =
        _weather.asStateFlow()

    fun loadWeather() {
        viewModelScope.launch {
            try {
                _weather.value = repository.getCurrentWeather(
                    55.75,
                    37.62
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}