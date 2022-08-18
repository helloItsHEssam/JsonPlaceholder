package com.iamhessam.jsonplaceholder.ui.screen.main.home.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeIntent
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeModel
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeViewState
import com.iamhessam.jsonplaceholder.utils.constant.CallBack
import com.iamhessam.jsonplaceholder.utils.extension.collectAsStateLifecycleAware

@Composable
fun HomeScreen(navController: NavController) {
    val model = hiltViewModel<HomeModel>()

    val viewState =
        model.states().collectAsStateLifecycleAware(HomeViewState.init)

    HomeBodyScreen(state = viewState) {
        model.processorIntent(HomeIntent.FetchComment)
//                navController.navigate(BottomBarDestination.Home.route)
    }
}

@Composable
fun HomeBodyScreen(
    state: State<HomeViewState>,
    callBack: CallBack,
) {
    Log.d("FetchDataKtor is", state.value.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                callBack()
            },
            text = "Home",
            color = Color.White
        )
    }
}