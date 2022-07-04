package com.iamhessam.jsonplaceholder.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamhessam.jsonplaceholder.ui.MainScreen
import com.iamhessam.jsonplaceholder.ui.navigation.Screen
import com.iamhessam.jsonplaceholder.ui.screen.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}