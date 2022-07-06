package com.iamhessam.jsonplaceholder.ui.navigation.destination

sealed class ProfileDestination: Destination {

    object User : ProfileDestination()

    override val route: String
        get() = when (this) {
            is User -> "user"
        }
}