package com.avcoding.core_local.di

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val HEADER_MODE = stringPreferencesKey("header_mode")
    val AUTH_TOKEN = stringPreferencesKey("auth_token")
}