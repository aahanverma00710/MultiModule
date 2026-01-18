package com.avcoding.core_network.client

import com.avcoding.core_network.api.ApiService
import com.avcoding.core_network.core.ApiKey
import com.avcoding.core_network.core.NetworkResult
import com.avcoding.core_network.core.safeApiCall
import javax.inject.Inject

/**
 * High-level network executor.
 * Feature modules interact only with this class.
 */
class NetworkClient @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun <T> get(
        url: String,
        query: Map<String, String> = emptyMap(),
        apiKey: ApiKey
    ): NetworkResult<T> =
        safeApiCall(apiKey) {
            apiService.get(url, query)
        }

    suspend fun <T> post(
        url: String,
        body: Any? = null,
        apiKey: ApiKey
    ): NetworkResult<T> =
        safeApiCall(apiKey) {
            apiService.post(url, body)
        }

    suspend fun <T> put(
        url: String,
        body: Any? = null,
        apiKey: ApiKey
    ): NetworkResult<T> =
        safeApiCall(apiKey) {
            apiService.put(url, body)
        }

    suspend fun <T> delete(
        url: String,
        query: Map<String, String> = emptyMap(),
        apiKey: ApiKey
    ): NetworkResult<T> =
        safeApiCall(apiKey) {
            apiService.delete(url, query)
        }
}
