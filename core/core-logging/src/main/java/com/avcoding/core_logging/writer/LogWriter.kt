package com.avcoding.core_logging.writer

import com.avcoding.core_logging.model.LogEvent

interface LogWriter {
    fun write(event: LogEvent)
}