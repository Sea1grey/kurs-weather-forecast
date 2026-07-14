package com.sergey.weatherforecast.ui

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

fun weatherDescription(code: Int): String =
    when (code) {
        0 -> "Clear sky"

        1, 2, 3 -> "Partly cloudy"

        45, 48 -> "Fog"

        51, 53, 55 -> "Drizzle"

        61, 63, 65 -> "Rain"

        71, 73, 75 -> "Snow"

        95 -> "Thunderstorm"

        else -> "Unknown"
    }