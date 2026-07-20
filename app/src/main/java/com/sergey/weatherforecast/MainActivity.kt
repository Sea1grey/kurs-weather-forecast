package com.sergey.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sergey.weatherforecast.ui.theme.WeatherForecastTheme
import com.sergey.weatherforecast.viewmodel.WeatherViewModel
import com.sergey.weatherforecast.data.repository.WeatherRepository
import com.sergey.weatherforecast.data.local.AppDatabase
import com.sergey.weatherforecast.viewmodel.WeatherViewModelFactory
import com.sergey.weatherforecast.data.remote.WeatherApi
import com.sergey.weatherforecast.ui.screens.WeatherScreen
import com.sergey.weatherforecast.data.remote.CityApi
import com.sergey.weatherforecast.data.preferences.DataStoreManager
import com.sergey.weatherforecast.ui.navigation.Navigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val database = AppDatabase.create(applicationContext)

            val dataStoreManager =
                DataStoreManager(applicationContext)

            val repository = WeatherRepository(
                    database.cityDao(),
                    WeatherApi.create(),
                    CityApi.create(),
                    dataStoreManager
            )

            val factory = WeatherViewModelFactory(repository)

            val viewModel: WeatherViewModel = viewModel(
                factory = factory
            )

            WeatherForecastTheme {
                Navigation(viewModel)
            }
        }
    }
}
