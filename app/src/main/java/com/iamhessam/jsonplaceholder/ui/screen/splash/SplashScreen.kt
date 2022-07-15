package com.iamhessam.jsonplaceholder.ui.screen.splash

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iamhessam.jsonplaceholder.ui.component.text.BodyText
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeIntent
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeModel
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeViewState
import com.iamhessam.jsonplaceholder.ui.theme.appColors
import com.iamhessam.jsonplaceholder.utils.extension.collectAsStateLifecycleAware

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {
    val model = hiltViewModel<HomeModel>()

    val viewState =
        model.states().collectAsStateLifecycleAware(HomeViewState.init)
    SplashBodyScreen(state = viewState) {
        model.processorIntent(HomeIntent.Initial)
    }
}

@Composable
private fun SplashBodyScreen(state: State<HomeViewState>, callBack: () -> Unit) {
    Log.d("HESSSSAMNEEEEE", state.value.toString())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.appColors.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        BodyText(text = "splash") {
            callBack()
//                navController.navigate(AppDestination.Main.route)
        }
    }
}