package com.example.utils

sealed class RequestResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : RequestResult<T>(data)

    class Error<T>(message: String?, data: T? = null) : RequestResult<T>(data, message)

    class Loading<T> : RequestResult<T>()
}

fun <T : Any> Result<T>.toRequestResult(): RequestResult<T> {
    return when {
        isSuccess -> RequestResult.Success(getOrThrow())
        isFailure -> RequestResult.Error(null)
        else -> error("Impossible branch")
    }
}