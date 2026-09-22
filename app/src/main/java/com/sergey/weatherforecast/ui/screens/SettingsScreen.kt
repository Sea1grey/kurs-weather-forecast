package com.sergey.weatherforecast.ui.screens
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import com.sergey.weatherforecast.ui.components.SettingsItem
import androidx.compose.ui.res.stringResource
import com.sergey.weatherforecast.R
import com.sergey.weatherforecast.BuildConfig

@Composable
fun SettingsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        SettingsItem(
            title = stringResource(R.string.language),
            value = stringResource(R.string.system_language)
        )

        SettingsItem(
            title = stringResource(R.string.version),
            value = BuildConfig.VERSION_NAME
        )

        SettingsItem(
            title = stringResource(R.string.developer),
            value = stringResource(R.string.developer_name)
        )
    }
}