package com.iamhessam.jsonplaceholder.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeIntent
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeModel
import com.iamhessam.jsonplaceholder.ui.theme.appColors

@Composable
fun SplashScreen(navController: NavController) {
    val model = hiltViewModel<HomeModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.appColors.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                model.processorIntent(HomeIntent.Initial)
//                navController.navigate(AppDestination.Main.route)
            },
            text = "Splash",
            color = MaterialTheme.appColors.titleColor
        )
    }
}