package com.iamhessam.jsonplaceholder.ui.navigation.destination

sealed class BottomBarDestination(val title: String) : Destination {

    object Home : BottomBarDestination("home")
    object Settings : BottomBarDestination("setting")
    object Profile : BottomBarDestination("profile")

    override val route: String
        get() = when (this) {
            is Home -> "home"
            is Settings -> "settings"
            is Profile -> "profile"
        }
}