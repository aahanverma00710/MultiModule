package com.avcoding.core_logging

import com.avcoding.core_logging.model.LogEvent
import com.avcoding.core_logging.model.LogLevel
import com.avcoding.core_logging.model.LoggerConfig
import com.avcoding.core_logging.writer.ConsoleLogWriter
import com.avcoding.core_logging.writer.FileLogWriter

/**
 * Central logger used across the app.
 */
object Logger {

    private lateinit var config: LoggerConfig
    private lateinit var fileWriter: FileLogWriter
    private var consoleWriter: ConsoleLogWriter? = null

    internal fun init(
        config: LoggerConfig,
        fileWriter: FileLogWriter,
        consoleWriter: ConsoleLogWriter?
    ) {
        this.config = config
        this.fileWriter = fileWriter
        this.consoleWriter = consoleWriter
    }

    fun d(tag: String, message: String, meta: Map<String, Any?> = emptyMap()) {
        log(LogLevel.DEBUG, tag, message, null, meta)
    }

    fun e(
        tag: String,
        message: String,
        throwable: Throwable? = null,
        meta: Map<String, Any?> = emptyMap()
    ) {
        log(LogLevel.ERROR, tag, message, throwable, meta)
    }

    private fun log(
        level: LogLevel,
        tag: String,
        message: String,
        throwable: Throwable?,
        meta: Map<String, Any?>
    ) {
        if (!config.isLoggingEnabled) return

        val event = LogEvent(
            level = level,
            tag = tag,
            message = message,
            throwable = throwable,
            metadata = meta
        )

        // Always write to file
        fileWriter.write(event)

        // Console only if enabled (debug)
        if (config.enableConsoleLogs) {
            consoleWriter?.write(event)
        }
    }
}
