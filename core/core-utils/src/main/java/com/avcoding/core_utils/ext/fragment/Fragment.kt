package com.avcoding.core_utils.ext.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Replaces a Fragment inside the specified container.
 *
 * @param containerId View ID hosting the fragment.
 * @param fragment    Fragment instance to display.
 * @param tag         Optional fragment tag for identification.
 *
 * Uses commitAllowingStateLoss to avoid IllegalStateException
 * during lifecycle edge cases.
 */
fun AppCompatActivity.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    tag: String? = null
) {
    supportFragmentManager
        .beginTransaction()
        .replace(containerId, fragment, tag)
        .commitAllowingStateLoss()
}
