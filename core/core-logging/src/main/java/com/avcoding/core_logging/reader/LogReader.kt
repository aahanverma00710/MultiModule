package com.avcoding.core_logging.reader

import com.avcoding.core_logging.file.LogFileInfo
import com.avcoding.core_logging.file.LogFileProvider
import java.io.File

/**
 * Read-only access to stored logs.
 */
class LogReader internal constructor(
    private val provider: LogFileProvider
) {

    /** Returns available daily log files */
    fun getLogFiles(): List<LogFileInfo> =
        provider.getLogFiles()

    /** Returns raw log lines for a given file */
    fun readLogs(fileInfo: LogFileInfo): List<String> {
        val file = File(provider.logDir, fileInfo.name)
        if (!file.exists()) return emptyList()
        return file.readLines()
    }
}