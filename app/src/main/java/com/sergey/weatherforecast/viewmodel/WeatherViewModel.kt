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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weather =
        MutableStateFlow<WeatherResponse?>(null)

    private val _searchText = MutableStateFlow("")

    private val _cityName = MutableStateFlow("")

    private var currentCity: CityResponse? = null

    val cityName: StateFlow<String> = _cityName.asStateFlow()

    val searchText: StateFlow<String> = _searchText.asStateFlow()

    val cities: Flow<List<CityEntity>> =
        repository.getCities()

    private companion object {

        const val DEFAULT_CITY = "Moscow"

        const val DEFAULT_LAT = 55.75

        const val DEFAULT_LON = 37.62
    }

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

            _searchText.value = ""
        }
    }

    fun loadLastCity() {

        viewModelScope.launch {

            val cityName = repository.getLastCity().first()

            if (cityName == null) {
                _cityName.value = DEFAULT_CITY

                _weather.value =
                    repository.getCurrentWeather(
                        DEFAULT_LAT,
                        DEFAULT_LON
                    )

                return@launch
            }

            val city =
                repository.getCityByName(cityName)
                    ?: return@launch

            _cityName.value = city.name

            _weather.value =
                repository.getCurrentWeather(
                    city.latitude,
                    city.longitude
                )
        }
    }


    fun searchWeather() {

        viewModelScope.launch {

            try {

                val city = repository.searchCity(searchText.value)
                    ?: return@launch

                currentCity = city

                _cityName.value = city.name

                repository.saveLastCity(city.name)

                _weather.value =
                    repository.getCurrentWeather(
                        city.latitude,
                        city.longitude
                    )

                _searchText.value = ""

            } catch (e: Exception) {
                e.printStackTrace()
            }
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