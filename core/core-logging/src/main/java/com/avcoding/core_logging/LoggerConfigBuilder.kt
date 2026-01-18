package com.avcoding.core_logging

import com.avcoding.core_logging.model.LoggerConfig

/**
 * DSL-style builder for clean init.
 */
class LoggerConfigBuilder {

    var isLoggingEnabled: Boolean = true
    var retentionDays: Int = 7
    var enableConsoleLogs: Boolean = false

    fun build(): LoggerConfig =
        LoggerConfig(
            isLoggingEnabled = isLoggingEnabled,
            retentionDays = retentionDays,
            enableConsoleLogs = enableConsoleLogs
        )
}