package com.iamhessam.jsonplaceholder.utils.exception

sealed class ArashniaException(
    override val message: String,
    val showType: ExceptionShowType = ExceptionShowType.TOAST
) : Exception(message) {
    data class AuthException(val _message: String) :
        ArashniaException(_message, ExceptionShowType.TOAST)

    data class NotFoundException(
        val _message: String,
    ) : ArashniaException(_message, ExceptionShowType.SNACK)
}