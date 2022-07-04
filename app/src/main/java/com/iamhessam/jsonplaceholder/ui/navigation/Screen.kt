package com.iamhessam.jsonplaceholder.ui.navigation

sealed class Screen(val route: String) {

    // main screens
    object Splash: Screen("splash_screen")

    object Main: Screen("main_screen")

    // nested profile screens
    object User: Screen("user_screen")
}

sealed class BottomBarScreen(val route: String, val title: String) {

    object Home: BottomBarScreen("home_screen", "home")
    object Settings: BottomBarScreen("settings_screen", "setting")
    object Profile: BottomBarScreen("profile_screen", "profile")
}