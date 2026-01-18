package com.avcoding.core_logging.file
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Deletes log files older than N days.
 */
class LogRetentionPolicy(
    private val keepDays: Int
) {

    fun apply(logDir: File) {
        val cutoff =
            System.currentTimeMillis() - TimeUnit.DAYS.toMillis(keepDays.toLong())

        logDir.listFiles()?.forEach { file ->
            if (file.lastModified() < cutoff) {
                file.delete()
            }
        }
    }
}