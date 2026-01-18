package com.avcoding.core_logging.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

/**
 * Date helpers for logging module.
 * Handles backward compatibility internally.
 */
object LogDateUtils {

    private const val LOG_DATE_PATTERN = "yyyy-MM-dd"

    /**
     * Parses a date from log file name (yyyy-MM-dd).
     */
    fun parseDate(dateString: String): Date? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            runCatching {
                LocalDate
                    .parse(dateString)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
                    .let { Date.from(it) }
            }.getOrNull()
        } else {
            runCatching {
                SimpleDateFormat(
                    LOG_DATE_PATTERN,
                    Locale.US
                ).parse(dateString)
            }.getOrNull()
        }
    }

    /**
     * Formats today's date for file naming.
     */
    fun todayAsString(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now().toString()
        } else {
            SimpleDateFormat(
                LOG_DATE_PATTERN,
                Locale.US
            ).format(Date())
        }
    }
}