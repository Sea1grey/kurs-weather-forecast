package com.sergey.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sergey.weatherforecast.ui.theme.WeatherForecastTheme
import com.sergey.weatherforecast.viewmodel.WeatherViewModel
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            WeatherForecastTheme {
                WeatherScreen()
            }
        }
    }
}

@Composable
fun WeatherScreen() {
    val viewModel: WeatherViewModel = viewModel()

    val weather by viewModel.weather.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }

    androidx.compose.material3.Surface {
        Text(
            text = weather?.current?.temperature_2m?.toString() ?: "Loading..."
        )
    }
}