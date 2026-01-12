package com.avcoding.core_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AutoHeaderInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val headerModeStore: HeaderModeStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (headerModeStore.getHeaderMode() == HeaderMode.AUTH) {
            tokenProvider.getToken()?.let {
                builder.addHeader("Authorization", "Bearer $it")
            }
        }

        return chain.proceed(builder.build())
    }
}
