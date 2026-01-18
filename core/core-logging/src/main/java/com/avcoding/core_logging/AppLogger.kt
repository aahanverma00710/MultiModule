package com.avcoding.core_logging

import android.content.Context
import com.avcoding.core_logging.file.LogFileProvider
import com.avcoding.core_logging.reader.LogReader

/**
 * Public facade.
 * Only entry point the app should use.
 */
object AppLogger {

    private lateinit var logReader: LogReader

    fun init(
        context: Context,
        block: LoggerConfigBuilder.() -> Unit
    ) {
        val builder = LoggerConfigBuilder().apply(block)
        val config = builder.build()

        val provider = LogFileProvider(context)

        LoggerBootstrapper.bootstrap(
            config = config,
            provider = provider
        )

        // 👇 NEW
        logReader = LogReader(provider)
    }

    /** Public read-only access to logs */
    fun reader(): LogReader = logReader
}
