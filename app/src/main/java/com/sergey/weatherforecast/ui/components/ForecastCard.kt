package com.sergey.weatherforecast.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sergey.weatherforecast.ui.weatherIcon
import com.sergey.weatherforecast.ui.weatherDescription
import androidx.compose.ui.platform.LocalContext
import com.sergey.weatherforecast.ui.formatForecastDate
import androidx.compose.ui.res.stringResource

@Composable
fun ForecastCard(
    day: String,
    maxTemp: Double,
    minTemp: Double,
    weatherCode: Int
) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = formatForecastDate(
                    context,
                    day
                )
            )

            Text("${maxTemp.toInt()}° / ${minTemp.toInt()}°")

            Text(
                text = weatherIcon(weatherCode)
            )

            Text(
                text = stringResource(
                    weatherDescription(weatherCode)
                )
            )

        }
    }
}