package com.sergey.weatherforecast.ui

import android.content.Context
import com.sergey.weatherforecast.R
import java.text.SimpleDateFormat
import java.util.Locale

fun formatForecastDate(
    context: Context,
    date: String
): String {

    val inputFormat = SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.getDefault()
    )

    val outputFormat = SimpleDateFormat(
        "EEE",
        Locale.getDefault()
    )

    val parsedDate = inputFormat.parse(date) ?: return date

    val today = inputFormat.format(System.currentTimeMillis())

    val tomorrowMillis =
        System.currentTimeMillis() + 24 * 60 * 60 * 1000L

    val tomorrow = inputFormat.format(tomorrowMillis)

    return when (date) {

        today ->
            context.getString(R.string.today)

        tomorrow ->
            context.getString(R.string.tomorrow)

        else ->
            outputFormat.format(parsedDate)
    }
}