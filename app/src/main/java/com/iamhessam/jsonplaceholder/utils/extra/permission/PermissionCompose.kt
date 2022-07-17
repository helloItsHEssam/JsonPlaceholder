package com.iamhessam.jsonplaceholder.utils.extra.permission

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.iamhessam.jsonplaceholder.utils.constant.CallBack
import com.iamhessam.jsonplaceholder.utils.extension.findActivity

class RequestPermissionState(initRequest: Boolean, val permission: String) {
    var requestPermission by mutableStateOf(initRequest)
}

@Composable
fun rememberRequestPermissionsState(
    initRequest: Boolean = true,
    permissions: String
): RequestPermissionState {
    return remember {
        RequestPermissionState(initRequest, permissions)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(
    context: Context,
    requestState: RequestPermissionState,
    granted: CallBack? = null,
    showRational: CallBack? = null,
    permanentlyDenied: CallBack? = null
) {
    val permissionState =
        rememberPermissionState(permission = requestState.permission) { isGranted ->
            val permissionPermanentlyDenied = !ActivityCompat.shouldShowRequestPermissionRationale(
                context.findActivity(), requestState.permission
            ) && !isGranted

            if (permissionPermanentlyDenied) {
                permanentlyDenied?.let { it() }
            } else if (!isGranted) {
                showRational?.let { it() }
            }
        }

    if (requestState.requestPermission) {
        requestState.requestPermission = false
        if (permissionState.status.isGranted) {
            granted?.let { it() }
        } else {
            LaunchedEffect(key1 = Unit) {
                permissionState.launchPermissionRequest()
            }
        }
    }
}