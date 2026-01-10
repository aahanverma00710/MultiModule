package com.avcoding.core_utils.ext.context

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * Checks whether the app has been granted a specific runtime permission.
 *
 * @param permission Android permission string
 *                   (e.g. Manifest.permission.CAMERA)
 * @return true if permission is granted, false otherwise
 */
fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

/**
 * Checks whether **all** permissions in the provided array are granted.
 *
 * @param permissions Array of Android permission strings
 * @return true if all permissions are granted, false otherwise
 *
 * Common use cases:
 * - Camera + Microphone
 * - Location (Coarse + Fine)
 * - Media access permissions
 */
fun Context.hasPermissions(permissions: Array<String>): Boolean {
    return permissions.all { hasPermission(it) }
}

/**
 * Checks whether **any** permission in the provided array is granted.
 *
 * @param permissions Array of Android permission strings
 * @return true if at least one permission is granted
 *
 * Useful when your feature can partially work with limited access.
 */
fun Context.hasAnyPermission(permissions: Array<String>): Boolean {
    return permissions.any { hasPermission(it) }
}