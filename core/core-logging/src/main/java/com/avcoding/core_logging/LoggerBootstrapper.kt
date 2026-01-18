package com.avcoding.core_logging

import android.content.Context
import com.avcoding.core_logging.file.LogFileProvider
import com.avcoding.core_logging.file.LogRetentionPolicy
import com.avcoding.core_logging.model.LoggerConfig
import com.avcoding.core_logging.writer.ConsoleLogWriter
import com.avcoding.core_logging.writer.FileLogWriter

/**
 * Internal wiring. App never touches this.
 */
internal object LoggerBootstrapper {

    fun bootstrap(
        config: LoggerConfig,
        provider: LogFileProvider
    ) {
        val fileWriter = FileLogWriter(provider)
        val consoleWriter =
            if (config.enableConsoleLogs) ConsoleLogWriter() else null

        Logger.init(
            config = config,
            fileWriter = fileWriter,
            consoleWriter = consoleWriter
        )

        LogRetentionPolicy(config.retentionDays)
            .apply(provider.logDir)
    }
}