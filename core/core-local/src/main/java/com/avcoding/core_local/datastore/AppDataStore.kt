package com.avcoding.core_local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AppDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun <T> read(key: Preferences.Key<T>): T? =
        dataStore.data.first()[key]

    suspend fun <T> write(key: Preferences.Key<T>, value: T) {
        dataStore.edit { it[key] = value }
    }

    suspend fun clear(key: Preferences.Key<*>) {
        dataStore.edit { it.remove(key) }
    }
}
