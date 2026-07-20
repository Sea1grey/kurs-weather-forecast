package com.sergey.weatherforecast.ui.screens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sergey.weatherforecast.viewmodel.WeatherViewModel
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Box
import com.sergey.weatherforecast.ui.components.CityCard

@Composable
fun FavoritesScreen(
    viewModel: WeatherViewModel,
    onCityClick: () -> Unit

) {

    val cities by viewModel.cities.collectAsStateWithLifecycle(
        initialValue = emptyList()
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            items(cities) { city ->

                CityCard(
                    city = city,
                    onClick = {
                        viewModel.loadCity(city)
                        onCityClick()
                    },
                    onDelete = {
                        viewModel.deleteCity(city)
                    }
                )
            }
        }
    }
}