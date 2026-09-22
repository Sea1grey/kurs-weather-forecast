package com.sergey.weatherforecast.ui.screens
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sergey.weatherforecast.viewmodel.WeatherViewModel
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.material3.Surface
import com.sergey.weatherforecast.ui.components.SearchBar
import com.sergey.weatherforecast.ui.components.CurrentWeatherCard
import com.sergey.weatherforecast.ui.components.ForecastCard
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.res.stringResource
import com.sergey.weatherforecast.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.fillMaxWidth

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {

    val weather by viewModel.weather.collectAsStateWithLifecycle()

    val searchText by viewModel.searchText
        .collectAsStateWithLifecycle()

    val cityName by viewModel.cityName.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadLastCity()
    }

    Surface {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Text(
                    text = cityName,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            item{
                SearchBar(
                    searchText = searchText,
                    onTextChange = viewModel::updateSearchText,
                    onSearch = viewModel::searchWeather
                )
            }

            item{
                weather?.let { currentWeather ->
                    CurrentWeatherCard(
                        weather = currentWeather,
                        cityName = cityName
                    )
                }
            }

            weather?.let {
                item {
                    Button(
                        modifier =
                            Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.addCurrentCity()
                        }
                    ) {
                        Text(
                            stringResource(R.string.favorite)
                        )
                    }
                }
            }

            weather?.daily?.let { daily ->

                item {
                    Text(
                        text = stringResource(R.string.forecast_7_days),
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                items(daily.time.size) { index ->

                    ForecastCard(
                        day = daily.time[index],
                        maxTemp = daily.temperature_2m_max[index],
                        minTemp = daily.temperature_2m_min[index],
                        weatherCode = daily.weather_code[index]
                    )
                }
            }
        }
    }
}