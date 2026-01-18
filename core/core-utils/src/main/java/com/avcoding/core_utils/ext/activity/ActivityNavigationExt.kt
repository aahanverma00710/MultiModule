package com.avcoding.core_utils.ext.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Navigates from the current Activity to another Activity.
 *
 * @param destination   Target Activity class.
 * @param bundle        Optional extras to pass to the destination.
 * @param finishCurrent If true, clears the back stack.
 *
 * Suitable for simple navigation flows outside of Navigation Component.
 */
fun AppCompatActivity.navigateTo(
    destination: Class<*>,
    bundle: Bundle? = null,
    finishCurrent: Boolean = false
) {
    val intent = Intent(this, destination)
    bundle?.let(intent::putExtras)
    startActivity(intent)

    if (finishCurrent) {
        finishAffinity()
    }
}
