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
import com.sergey.weatherforecast.ui.weatherIcon
import com.sergey.weatherforecast.ui.weatherDescription
import androidx.compose.ui.res.stringResource
import com.sergey.weatherforecast.R

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
                text = cityName.ifBlank {
                    stringResource(R.string.current_location)
                }
            )

            Text(
                text = weather?.current?.temperature_2m?.let {
                    stringResource(
                        R.string.temperature_value,
                        it
                    )
                } ?: stringResource(R.string.no_data)
            )

            Text(
                text = weather?.current?.let {
                    weatherIcon(it.weather_code)
                } ?: ""
            )

            Text(
                text = weather?.current?.let {
                    stringResource(
                        weatherDescription(
                            weather.current.weather_code
                        )
                    )
                } ?: ""
            )

            Text(
                text = weather?.current?.wind_speed_10m?.let {
                    stringResource(
                        R.string.wind_value,
                        it
                    )
                } ?: ""
            )

        }
    }
}