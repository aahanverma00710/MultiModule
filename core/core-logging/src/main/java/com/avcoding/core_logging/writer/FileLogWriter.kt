package com.avcoding.core_logging.writer

import com.avcoding.core_logging.file.LogFileProvider
import com.avcoding.core_logging.model.LogEvent
import kotlinx.coroutines.*

/**
 * Async file writer (never blocks UI).
 */
class FileLogWriter(
    private val provider: LogFileProvider
) : LogWriter {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun write(event: LogEvent) {
        scope.launch {
            provider.append(event)
        }
    }
}