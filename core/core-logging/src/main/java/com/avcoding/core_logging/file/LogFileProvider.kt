package com.avcoding.core_logging.file

import android.content.Context
import com.avcoding.core_logging.model.LogEvent
import com.avcoding.core_logging.utils.LogDateUtils
import java.io.File

/**
 * Owns filesystem access for logs.
 */
/**
 * Owns all filesystem operations for logs.
 * - Creates daily log files
 * - Appends log entries
 * - Lists available log files
 */
class LogFileProvider(context: Context) {

    /** Directory where all log files live */
    internal val logDir: File =
        File(context.filesDir, "logs").apply { mkdirs() }

    /**
     * Appends a log event to today's log file.
     * File name format: log_yyyy-MM-dd.jsonl
     */
    fun append(event: LogEvent) {
        val fileName = buildFileName(LogDateUtils.todayAsString())
        File(logDir, fileName)
            .appendText(event.toString() + "\n")
    }

    /**
     * Returns all valid log files present in the directory.
     * (Invalid / malformed files are ignored safely)
     */
    fun getLogFiles(): List<LogFileInfo> {
        return logDir.listFiles()
            ?.mapNotNull { LogFileInfo.from(it) }
            ?.sortedByDescending { it.date.time } // newest first
            ?: emptyList()
    }

    /**
     * Builds log file name from date string.
     */
    private fun buildFileName(dateString: String): String =
        "log_${dateString}.jsonl"
}