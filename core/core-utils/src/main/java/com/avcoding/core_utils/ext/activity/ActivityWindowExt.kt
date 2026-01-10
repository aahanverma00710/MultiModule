package com.happinessai.core.ext.activity

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.view.ViewCompat

/**
 * Enables immersive fullscreen mode for the Activity.
 *
 * - Keeps the screen awake while the Activity is visible.
 * - Hides system bars (status & navigation bars).
 * - Supports swipe gesture to temporarily reveal system bars.
 *
 * Use this for experiences like:
 * - Video playback
 * - Camera screens
 * - Meditation / focus flows
 */
fun Activity.enableFullScreen() {
    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.let {
            it.hide(WindowInsets.Type.systemBars())
            it.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}

/**
 * Disables fullscreen mode and restores system UI visibility.
 *
 * Call this when leaving immersive screens to avoid UI issues
 * in subsequent Activities or Fragments.
 */
fun Activity.disableFullScreen() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.show(WindowInsets.Type.systemBars())
    } else {
        @Suppress("DEPRECATION")
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}

/**
 * Updates the status bar background color and icon appearance.
 *
 * @param color   Resolved color integer (use ContextCompat.getColor).
 * @param isLight Whether status bar icons should be dark (true) or light (false).
 *
 * Useful for aligning system UI with light/dark screens.
 */
fun Activity.changeStatusBarColor(color: Int, isLight: Boolean) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color
    ViewCompat.getWindowInsetsController(window.decorView)
        ?.isAppearanceLightStatusBars = isLight
}
