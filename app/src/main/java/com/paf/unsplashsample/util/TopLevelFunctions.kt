package com.paf.unsplashsample.util

import com.paf.unsplashsample.network.NetworkResult

suspend fun<T> safeApiCall(
    call: suspend () -> T,
    onSuccess: (NetworkResult.Success<T>) -> Unit,
    onFailure: (NetworkResult.Error) -> Unit
) {
    runCatching {
        val response = call()
        onSuccess(NetworkResult.Success(response))
    }.onFailure { message ->
        onFailure(NetworkResult.Error(message.message.toString()))
    }
}