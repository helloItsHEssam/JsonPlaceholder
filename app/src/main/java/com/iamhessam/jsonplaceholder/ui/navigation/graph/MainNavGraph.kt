package com.iamhessam.jsonplaceholder.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamhessam.jsonplaceholder.ui.navigation.destination.BottomBarDestination
import com.iamhessam.jsonplaceholder.ui.screen.main.home.HomeScreen
import com.iamhessam.jsonplaceholder.ui.screen.main.settings.SettingScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = "Main",
        startDestination = BottomBarDestination.Home.route
    ) {

        composable(BottomBarDestination.Home.route) {
            HomeScreen(navController)
        }

        composable(BottomBarDestination.Settings.route) {
            SettingScreen(navController = navController)
        }

        profileGraph(navController)
    }
}