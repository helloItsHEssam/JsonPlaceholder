package com.iamhessam.jsonplaceholder.ui.navigation.destination

sealed class AppDestination : Destination {

    object Splash : AppDestination()
    object Main : AppDestination()

    override val route: String
        get() = when (this) {
            is Splash -> "splash"
            is Main -> "main"
        }
}