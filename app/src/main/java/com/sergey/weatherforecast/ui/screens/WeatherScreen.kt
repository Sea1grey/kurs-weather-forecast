package com.sergey.weatherforecast.ui.screens
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sergey.weatherforecast.viewmodel.WeatherViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import com.sergey.weatherforecast.ui.components.SearchBar
import com.sergey.weatherforecast.ui.components.CurrentWeatherCard

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {

    val weather by viewModel.weather.collectAsStateWithLifecycle()

    val searchText by viewModel.searchText
        .collectAsStateWithLifecycle()

    val cityName by viewModel.cityName.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {

        viewModel.loadWeather()

        viewModel.loadLastCity()
    }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchBar(
                searchText = searchText,
                onTextChange = viewModel::updateSearchText,
                onSearch = viewModel::searchWeather
            )

            Spacer(modifier = Modifier.height(16.dp))

            weather?.let {
                CurrentWeatherCard(
                    weather = weather,
                    cityName = cityName,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.addCurrentCity()
                }
            ) {
                Text("⭐ Add to favorites")
            }

        }
    }
}