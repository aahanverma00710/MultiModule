package com.avcoding.core_logging.model

/**
 * Structured log entry.
 * Stored as JSON line.
 */
data class LogEvent(
    val timestamp: Long = System.currentTimeMillis(),
    val level: LogLevel,
    val tag: String,
    val message: String,
    val throwable: Throwable?,
    val metadata: Map<String, Any?>
)