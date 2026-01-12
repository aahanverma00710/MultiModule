package com.avcoding.core_network.core

/**
 * Marker contract for identifying API calls.
 *
 * Implemented by feature modules using enums or objects.
 * This file NEVER changes.
 */
sealed interface ApiKey {
    val value: String
}
