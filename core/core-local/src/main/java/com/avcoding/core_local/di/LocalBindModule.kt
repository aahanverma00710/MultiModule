package com.avcoding.core_local.di

import com.avcoding.core_local.session.SessionLocalDataSource
import com.avcoding.core_network.interceptor.TokenProvider
import com.avcoding.core_network.interceptor.HeaderModeStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Binds local session implementations to their interfaces.
 *
 * core-network depends ONLY on TokenProvider / HeaderModeStore,
 * core-local provides the concrete implementation.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class LocalBindModule {

    @Binds
    @Singleton
    abstract fun bindTokenProvider(
        impl: SessionLocalDataSource
    ): TokenProvider

    @Binds
    @Singleton
    abstract fun bindHeaderModeStore(
        impl: SessionLocalDataSource
    ): HeaderModeStore
}
