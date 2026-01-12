package com.avcoding.core_network.interceptor

/**
 * Abstraction over local storage (Prefs/DataStore)
 * that decides which headers to apply.
 */
interface HeaderModeStore {
   fun getHeaderMode(): HeaderMode
}
