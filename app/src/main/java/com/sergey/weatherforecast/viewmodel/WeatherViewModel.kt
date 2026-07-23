package com.sergey.weatherforecast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergey.weatherforecast.data.remote.WeatherResponse
import com.sergey.weatherforecast.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.sergey.weatherforecast.data.remote.CityResponse
import com.sergey.weatherforecast.data.local.CityEntity
import com.sergey.weatherforecast.data.preferences.DataStoreManager
import kotlinx.coroutines.flow.Flow

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weather =
        MutableStateFlow<WeatherResponse?>(null)

    private val _searchText = MutableStateFlow("")

    private val _cityName = MutableStateFlow("Moscow")

    private var currentCity: CityResponse? = null

    val cityName: StateFlow<String> = _cityName.asStateFlow()

    val searchText: StateFlow<String> = _searchText.asStateFlow()

    val cities: Flow<List<CityEntity>> =
        repository.getCities()

    fun updateSearchText(text: String) {
        _searchText.value = text
    }

    val weather: StateFlow<WeatherResponse?> =
        _weather.asStateFlow()

    fun loadCity(city: CityEntity) {

        viewModelScope.launch {

            _cityName.value = city.name
            repository.saveLastCity(city.name)

            _weather.value =
                repository.getCurrentWeather(
                    city.latitude,
                    city.longitude
                )
        }
    }

    fun loadLastCity() {

        viewModelScope.launch {

            repository.getLastCity().collect { cityName ->

                if (cityName == null) {
                    _cityName.value = "Moscow"

                    _weather.value =
                        repository.getCurrentWeather(
                            55.75,
                            37.62
                        )

                    return@collect
                }


                val city =
                    repository.getCityByName(cityName)
                        ?: return@collect

                _cityName.value = city.name

                _weather.value =
                    repository.getCurrentWeather(
                        city.latitude,
                        city.longitude
                    )
            }
        }
    }


    fun searchWeather() {
        viewModelScope.launch {
            try {
                val city = repository.searchCity(searchText.value)
                    ?: return@launch

                _weather.value = repository.getCurrentWeather(
                    city.latitude,
                    city.longitude
                )

                _cityName.value = city.name
                repository.saveLastCity(city.name)

            } catch (e: Exception) {
                e.printStackTrace()
            }
            val city = repository.searchCity(searchText.value)
                ?: return@launch

            currentCity = city

            _cityName.value = city.name

            _weather.value = repository.getCurrentWeather(
                city.latitude,
                city.longitude
            )
        }

    }

    fun addCurrentCity() {

        val city = currentCity ?: return

        viewModelScope.launch {

            if (repository.cityExists(city.name)) {
                return@launch
            }

            repository.addCity(
                CityEntity(
                    name = city.name,
                    latitude = city.latitude,
                    longitude = city.longitude
                )
            )
        }
    }

    fun deleteCity(city: CityEntity) {

        viewModelScope.launch {
            repository.deleteCity(city)
        }
    }
}