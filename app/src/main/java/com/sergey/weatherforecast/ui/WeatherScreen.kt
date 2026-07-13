package com.sergey.weatherforecast.ui
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sergey.weatherforecast.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {

    val weather by viewModel.weather.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }

    androidx.compose.material3.Surface {
        Text(
            text = weather?.current?.temperature_2m?.toString()
                ?: "Loading..."
        )
    }
}