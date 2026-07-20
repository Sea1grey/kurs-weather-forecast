package com.sergey.weatherforecast.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sergey.weatherforecast.ui.screens.FavoritesScreen
import com.sergey.weatherforecast.ui.screens.SettingsScreen
import com.sergey.weatherforecast.ui.screens.WeatherScreen
import com.sergey.weatherforecast.viewmodel.WeatherViewModel
import com.sergey.weatherforecast.ui.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding

@Composable
fun Navigation(
    viewModel: WeatherViewModel
) {

    val navController = rememberNavController()

    Scaffold(

        bottomBar = {

            val navBackStackEntry =
                navController.currentBackStackEntryAsState()

            val currentRoute =
                navBackStackEntry.value?.destination?.route

            NavigationBar {

                NavigationBarItem(
                    selected = currentRoute == Screen.Weather.route,
                    onClick = {
                        navController.navigate(Screen.Weather.route){
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Default.Cloud,
                            contentDescription = "Weather"
                        )
                    },
                    label = {
                        Text("Weather")
                    }
                )

                NavigationBarItem(
                    selected = currentRoute == Screen.Favorites.route,
                    onClick = {
                        navController.navigate(Screen.Favorites.route){
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favorites"
                        )
                    },
                    label = {
                        Text("Favorites")
                    }
                )

                NavigationBarItem(
                    selected = currentRoute == Screen.Settings.route,
                    onClick = {
                        navController.navigate(Screen.Settings.route){
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    },
                    label = {
                        Text("Settings")
                    }
                )
            }
        }

    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Weather.route,
            modifier = Modifier.padding(padding)
        ) {

            composable(Screen.Weather.route) {
                WeatherScreen(viewModel)
            }

            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    viewModel = viewModel,
                    onCityClick = {
                        navController.navigate(Screen.Weather.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}