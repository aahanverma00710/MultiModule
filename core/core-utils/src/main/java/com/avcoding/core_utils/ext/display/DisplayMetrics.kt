package com.avcoding.core_utils.ext.display

import android.app.Activity
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import kotlin.math.roundToInt

/**
 * Provides the current display metrics of the Activity.
 *
 * Includes:
 * - Screen width & height in pixels
 * - Screen density
 * - Font scaling
 *
 * Automatically handles API-level differences.
 */
val Activity.displayMetrics: DisplayMetrics
    get() {
        val metrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            display?.getRealMetrics(metrics)
        } else {
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getMetrics(metrics)
        }
        return metrics
    }

/**
 * Returns screen size in density-independent pixels (dp).
 *
 * Useful for:
 * - Adaptive layouts
 * - Dynamic UI calculations
 */
val Activity.screenSizeInDp: Point
    get() = displayMetrics.run {
        Point(
            (widthPixels / density).roundToInt(),
            (heightPixels / density).roundToInt()
        )
    }

/**
 * Returns screen size in raw pixels (px).
 *
 * Useful for:
 * - Canvas drawing
 * - Bitmap calculations
 * - Precise UI positioning
 */
val Activity.screenSizeInPx: Point
    get() = displayMetrics.run {
        Point(widthPixels, heightPixels)
    }
