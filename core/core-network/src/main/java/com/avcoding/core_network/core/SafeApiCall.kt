package com.avcoding.core_network.core

import retrofit2.HttpException

/**
 * Executes a network call safely and reports
 * success / failure with optional ApiKey.
 */
suspend fun <T> safeApiCall(
    apiKey: ApiKey,
    call: suspend () -> T
): NetworkResult<T> {
    return try {
        val result = call()
        NetworkResult.Success(result,apiKey)
    } catch (e: HttpException) {
        NetworkResult.Error(e.code(), apiKey,e.message())
    } catch (e: Exception) {
        NetworkResult.NetworkFailure
    }
}
