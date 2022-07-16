package com.iamhessam.jsonplaceholder.ui.screen.splash

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.iamhessam.jsonplaceholder.ui.component.text.TextBody
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeIntent
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeModel
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeViewState
import com.iamhessam.jsonplaceholder.ui.theme.appColors
import com.iamhessam.jsonplaceholder.utils.constant.CallBack
import com.iamhessam.jsonplaceholder.utils.extension.collectAsStateLifecycleAware

@SuppressLint(
    "PermissionLaunchedDuringComposition",
    "CoroutineCreationDuringComposition"
)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(navController: NavController) {
    val model = hiltViewModel<HomeModel>()


    var permissionAlreadyRequested by remember {
        mutableStateOf(false)
    }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    Log.d("HESSSSAMNEEEEE", cameraPermissionState.status.toString())
    when {
        cameraPermissionState.status.shouldShowRationale ||
                !cameraPermissionState.status.shouldShowRationale -> {
            cameraPermissionState.launchPermissionRequest()
        }

        cameraPermissionState.status.isGranted -> {
            Log.d("HESSSSAMNEEEEE", "Grannnnted")
        }
    }


//
//    if (!permissionAlreadyRequested && !cameraPermissionState.shouldShowRationale) {
//        SideEffect {
//            cameraPermissionState.launchPermissionRequest()
//        }
//    } else if (cameraPermissionState.shouldShowRationale) {
//        ShowRationaleContent {
//            cameraPermissionState.launchPermissionRequest()
//        }
//    } else {
//        ShowOpenSettingsContent {
//            context.openSettings()
//        }
//    }


    val viewState =
        model.states().collectAsStateLifecycleAware(HomeViewState.init)
    SplashBodyScreen(state = viewState, callBack = {
        model.processorIntent(HomeIntent.Initial)
    }) {

//        model.processorIntent(HomeIntent.PullToRefresh)
    }
}

@Composable
private fun SplashBodyScreen(
    state: State<HomeViewState>, callBack: CallBack,
    callBack2: CallBack
) {
    Log.d("HESSSSAMNEEEEE", state.value.toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.appColors.backgroundColor),
    ) {
        TextBody(text = "splash") {
            callBack()
        }
        Spacer(modifier = Modifier.padding(5.dp))
        TextBody("SplashTwo") {
            callBack2()
        }
    }
}