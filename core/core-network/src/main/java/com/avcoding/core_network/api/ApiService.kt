package com.avcoding.core_network.api

import retrofit2.http.*

/**
 * Generic Retrofit service that supports
 * fully dynamic URLs using @Url.
 *
 * Feature modules never interact with this directly.
 */
interface ApiService {

    /**
     * Generic GET request
     */
    @GET
    suspend fun <T> get(
        @Url url: String,
        @QueryMap(encoded = true) query: Map<String, String> = emptyMap()
    ): T

    /**
     * Generic POST request
     */
    @POST
    suspend fun <T> post(
        @Url url: String,
        @Body body: Any?
    ): T

    /**
     * Generic PUT request
     */
    @PUT
    suspend fun <T> put(
        @Url url: String,
        @Body body: Any?
    ): T

    /**
     * Generic DELETE request
     */
    @DELETE
    suspend fun <T> delete(
        @Url url: String,
        @QueryMap(encoded = true) query: Map<String, String> = emptyMap()
    ): T
}
