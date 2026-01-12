package com.avcoding.core_local.session

import com.avcoding.core_local.datastore.AppDataStore
import com.avcoding.core_local.di.PreferenceKeys
import com.avcoding.core_network.interceptor.HeaderMode
import com.avcoding.core_network.interceptor.HeaderModeStore
import com.avcoding.core_network.interceptor.TokenProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SessionLocalDataSource @Inject constructor(
    private val appDataStore: AppDataStore
) : TokenProvider, HeaderModeStore {

    @Volatile private var token: String? = null
    @Volatile private var headerMode: HeaderMode = HeaderMode.NONE

    fun initSync() {
        runBlocking(Dispatchers.IO) {
            token = appDataStore.read(PreferenceKeys.AUTH_TOKEN)
            headerMode = appDataStore.read(PreferenceKeys.HEADER_MODE)
                ?.let { HeaderMode.valueOf(it) } ?: HeaderMode.NONE
        }
    }
    suspend fun onLogin(newToken: String) {
        token = newToken
        headerMode = HeaderMode.AUTH

        appDataStore.write(PreferenceKeys.AUTH_TOKEN, newToken)
        appDataStore.write(PreferenceKeys.HEADER_MODE, HeaderMode.AUTH.name)
    }

    suspend fun onLogout() {
        token = null
        headerMode = HeaderMode.NONE

        appDataStore.clear(PreferenceKeys.AUTH_TOKEN)
        appDataStore.write(PreferenceKeys.HEADER_MODE, HeaderMode.NONE.name)
    }

    override fun getToken(): String? {
        return token
    }

    override fun getHeaderMode(): HeaderMode {
        return headerMode
    }
}
