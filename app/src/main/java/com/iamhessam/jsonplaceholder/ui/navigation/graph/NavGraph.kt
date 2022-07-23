package com.iamhessam.jsonplaceholder.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamhessam.jsonplaceholder.ui.navigation.destination.AppDestination
import com.iamhessam.jsonplaceholder.ui.screen.main.MainScreen
import com.iamhessam.jsonplaceholder.ui.screen.splash.screen.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Splash.route
    ) {

        composable(AppDestination.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(AppDestination.Main.route) {
            MainScreen()
        }
    }
}