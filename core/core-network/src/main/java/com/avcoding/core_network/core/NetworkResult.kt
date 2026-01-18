package com.avcoding.core_network.core

/**
 * Unified result type for all network calls.
 */
sealed class NetworkResult<out T> {

    /**
     * Successful API response
     */
    data class Success<T>(val data: T,val apiKey: ApiKey) : NetworkResult<T>()

    /**
     * HTTP error (4xx / 5xx)
     */
    data class Error(
        val code: Int?,
        val apiKey: ApiKey,
        val message: String?,
    ) : NetworkResult<Nothing>()

    /**
     * Network / unexpected failure
     */
    object NetworkFailure : NetworkResult<Nothing>()
}
