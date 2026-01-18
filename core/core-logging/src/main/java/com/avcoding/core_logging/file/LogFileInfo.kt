package com.avcoding.core_logging.file

import com.avcoding.core_logging.utils.LogDateUtils
import java.io.File
import java.util.Date

/**
 * Lightweight file descriptor for UI later.
 */
data class LogFileInfo(
    val date: Date,
    val name: String,
    val sizeBytes: Long
) {
    companion object {
        fun from(file: File): LogFileInfo? {
            val datePart = file.name
                .removePrefix("log_")
                .removeSuffix(".jsonl")

            val parsedDate = LogDateUtils.parseDate(datePart)
                ?: return null

            return LogFileInfo(
                date = parsedDate,
                name = file.name,
                sizeBytes = file.length()
            )
        }
    }
}