package com.avcoding.core_logging.writer

import android.util.Log
import com.avcoding.core_logging.model.LogEvent
import com.avcoding.core_logging.model.LogLevel

/**
 * Debug-only console writer.
 */
class ConsoleLogWriter : LogWriter {

    override fun write(event: LogEvent) {
        when (event.level) {
            LogLevel.DEBUG -> Log.d(event.tag, event.message)
            LogLevel.INFO  -> Log.i(event.tag, event.message)
            LogLevel.WARN  -> Log.w(event.tag, event.message)
            LogLevel.ERROR -> Log.e(event.tag, event.message, event.throwable)
        }
    }
}