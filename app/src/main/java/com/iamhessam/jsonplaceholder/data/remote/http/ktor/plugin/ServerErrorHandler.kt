package com.iamhessam.jsonplaceholder.data.remote.http.ktor.plugin

import com.iamhessam.jsonplaceholder.utils.exception.ArashniaException

class ServerErrorHandler {

    operator fun invoke(statusCode: Int, message: String? = null): ArashniaException {

        return when (statusCode) {
            401 -> ArashniaException.AuthException(message ?: "Error")
            else -> ArashniaException.NotFoundException(message ?: "Error")
        }
    }
}