package com.avcoding.core_network.interceptor
interface TokenProvider {
    fun getToken(): String?
}