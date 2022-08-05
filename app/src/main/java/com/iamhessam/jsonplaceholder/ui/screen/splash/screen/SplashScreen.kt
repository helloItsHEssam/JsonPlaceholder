package com.iamhessam.jsonplaceholder.ui.screen.splash.screen

import android.Manifest
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.iamhessam.jsonplaceholder.ui.component.text.TextBody
import com.iamhessam.jsonplaceholder.ui.screen.splash.models.SplashIntent
import com.iamhessam.jsonplaceholder.ui.screen.splash.models.SplashModel
import com.iamhessam.jsonplaceholder.ui.screen.splash.models.SplashViewState
import com.iamhessam.jsonplaceholder.utils.constant.CallBack
import com.iamhessam.jsonplaceholder.utils.extension.appColors
import com.iamhessam.jsonplaceholder.utils.extension.collectAsStateLifecycleAware
import com.iamhessam.jsonplaceholder.utils.extra.permission.RequestPermission
import com.iamhessam.jsonplaceholder.utils.settings.theme.ActiveColor
import com.iamhessam.jsonplaceholder.utils.settings.theme.ThemeColor

/**
 *
 * @author hessam Mahdiabadi
 * @return Nothing Return
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(navController: NavController) {
    val model = hiltViewModel<SplashModel>()

    // get permission
    val context = LocalContext.current
    RequestPermission(
        context = context,
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

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val viewState =
        model.states().collectAsStateLifecycleAware(SplashViewState.init)

    model.processorIntent(SplashIntent.ReadingTheme)

    var hello by remember {
        mutableStateOf(true)
    }

    SplashBodyScreen(state = viewState, callBack = {
        launcher.launch("image/*")
    }) {
        if (hello) {
            model.processorIntent(SplashIntent.UpdateTheme(ActiveColor.User(ThemeColor.LIGHT)))
        } else {
            model.processorIntent(SplashIntent.UpdateTheme(ActiveColor.User(ThemeColor.DARK)))
        }
        hello = !hello

//        model.processorIntent(HomeIntent.PullToRefresh)
//        if (!context.checkHasPermission(Manifest.permission.CAMERA)) {
//            Log.d("HESSSSAMNEEEEE", "rrrrrrr")
//        }
    }
}

@Composable
private fun SplashBodyScreen(
    state: State<SplashViewState>,
    callBack: CallBack,
    callBack2: CallBack
) {
    Log.d("HEssam THEME Splash", state.value.toString())
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