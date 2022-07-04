package com.iamhessam.jsonplaceholder.ui.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamhessam.jsonplaceholder.ui.navigation.BottomBarScreen
import com.iamhessam.jsonplaceholder.ui.screen.main.home.HomeScreen
import com.iamhessam.jsonplaceholder.ui.screen.main.profile.ProfileScreen
import com.iamhessam.jsonplaceholder.ui.screen.main.settings.SettingScreen

fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    navigation(
        startDestination = BottomBarScreen.Home.route,
        route = "Home_route"
    ) {

        composable(BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }

        composable(BottomBarScreen.Settings.route) {
            SettingScreen(navController = navController)
        }

        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}
