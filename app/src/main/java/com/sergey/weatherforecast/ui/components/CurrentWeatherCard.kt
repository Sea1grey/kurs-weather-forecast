package com.sergey.weatherforecast.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sergey.weatherforecast.data.remote.WeatherResponse

@Composable
fun CurrentWeatherCard(
    weather: WeatherResponse?,
    cityName: String,
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = cityName.ifBlank { "Current location" }
            )

            Text(
                text = weather?.current?.temperature_2m?.let {
                    "Temperature: $it°C"
                } ?: "No data"
            )

            Text(
                text = weather?.current?.wind_speed_10m?.let {
                    "Wind: $it km/h"
                } ?: ""
            )

        }
    }
}