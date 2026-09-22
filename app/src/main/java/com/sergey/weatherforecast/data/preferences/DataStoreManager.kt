package com.sergey.weatherforecast.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

private val Context.dataStore by preferencesDataStore(
    name = "settings"
)

class DataStoreManager(
    private val context: Context
) {

    companion object {
        val LAST_CITY =
            stringPreferencesKey("last_city")
    }

    fun getLastCity(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[LAST_CITY]
        }
    }

    suspend fun saveLastCity(city: String) {

        context.dataStore.edit { preferences ->
            preferences[LAST_CITY] = city
        }
    }
}