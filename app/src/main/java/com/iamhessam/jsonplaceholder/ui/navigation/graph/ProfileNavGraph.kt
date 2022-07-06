package com.iamhessam.jsonplaceholder.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.iamhessam.jsonplaceholder.ui.navigation.destination.BottomBarDestination
import com.iamhessam.jsonplaceholder.ui.navigation.destination.ProfileDestination
import com.iamhessam.jsonplaceholder.ui.screen.main.profile.ProfileScreen
import com.iamhessam.jsonplaceholder.ui.screen.main.profile.user.UserScreen

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation(
        startDestination = BottomBarDestination.Profile.route,
        route = "profile_route"
    ) {

        composable(BottomBarDestination.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(ProfileDestination.User.route) {
            UserScreen(navController = navController)
        }
    }
}