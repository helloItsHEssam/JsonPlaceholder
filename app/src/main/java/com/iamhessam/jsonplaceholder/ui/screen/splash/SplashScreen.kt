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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.iamhessam.jsonplaceholder.ui.component.text.TextBody
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeModel
import com.iamhessam.jsonplaceholder.ui.screen.main.home.models.HomeViewState
import com.iamhessam.jsonplaceholder.utils.constant.CallBack
import com.iamhessam.jsonplaceholder.utils.extension.appColors
import com.iamhessam.jsonplaceholder.utils.extension.checkHasPermission
import com.iamhessam.jsonplaceholder.utils.extension.collectAsStateLifecycleAware
import com.iamhessam.jsonplaceholder.utils.extra.permission.RequestPermission

/**
 *
 * @author hessam
 * @return sample return
 */
@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint(
    "PermissionLaunchedDuringComposition",
    "CoroutineCreationDuringComposition"
)
@Composable
fun SplashScreen(navController: NavController) {
    val model = hiltViewModel<HomeModel>()

    // get permission
    val context = LocalContext.current
    RequestPermission(context = context,
        requestPermission = Manifest.permission.CAMERA,
        granted = {
            Log.d("HESSSSAMNEEEEE", "Granted")
        },
        showRational = {
            Log.d("HESSSSAMNEEEEE", "Show rational")
        },
        permanentlyDenied = {
            Log.d("HESSSSAMNEEEEE", "Denied")
        },
        chooseState = { st ->
            when (st) {
                is PermissionStatus.Granted -> {
                    Log.d("HESSSSAMNEEEEE", "Granted")
                }

                is PermissionStatus.Denied -> {
                    Log.d("HESSSSAMNEEEEE", "Denied")
                }
            }

        }
    )

    val viewState =
        model.states().collectAsStateLifecycleAware(HomeViewState.init)
    SplashBodyScreen(state = viewState, callBack = {
//        navController.navigate(AppDestination.Main.route)
    }) {
//        model.processorIntent(HomeIntent.PullToRefresh)
        if (!context.checkHasPermission(Manifest.permission.CAMERA)) {
            Log.d("HESSSSAMNEEEEE", "rrrrrrr")
        }
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