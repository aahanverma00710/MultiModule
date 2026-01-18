package com.avcoding.core_logging.model

/**
 * Immutable runtime configuration for logger.
 */
data class LoggerConfig(
    val isLoggingEnabled: Boolean,
    val retentionDays: Int,
    val enableConsoleLogs: Boolean
)