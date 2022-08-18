package com.iamhessam.jsonplaceholder.data.remote.http.ktor.plugin

import android.util.Log
import io.ktor.client.plugins.logging.*

class KtorLogging: Logger {

    companion object {
        const val TAG = "KtorLogging"
    }

    override fun log(message: String) {
        Log.d(TAG, message)
    }
}