package com.avcoding.core_utils.ext.context

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * Opens the application's system settings screen.
 *
 * Useful when directing users to manually enable permissions
 * after they have been permanently denied.
 */
fun Context.openAppSettings() {
    runCatching {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }
}

/**
 * Opens the given URL in the user's default browser.
 *
 * @param url Fully qualified URL (https://...)
 */
fun Context.openBrowser(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}
