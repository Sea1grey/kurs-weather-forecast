package com.sergey.weatherforecast.ui


import com.sergey.weatherforecast.R

fun weatherIcon(code: Int): String =
    when (code) {
        0 -> "☀️"

        1, 2, 3 -> "⛅"

        45, 48 -> "🌫"

        51, 53, 55 -> "🌦"

        61, 63, 65 -> "🌧"

        71, 73, 75 -> "❄️"

        95 -> "⛈"

        else -> "❓"
    }

fun weatherDescription(code: Int): Int =
    when (code) {

        0 -> R.string.clear_sky

        1, 2, 3 -> R.string.partly_cloudy

        45, 48 -> R.string.fog

        51, 53, 55 -> R.string.drizzle

        61, 63, 65 -> R.string.rain

        71, 73, 75 -> R.string.snow

        95 -> R.string.thunderstorm

        else -> R.string.unknown
    }