package com.iamhessam.jsonplaceholder.utils.extra.permission

interface PermissionListener {
    fun permissionGranted()
    fun permissionDenied()
}