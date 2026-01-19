package com.meghalife.app.data

/**
 * Represents a live bus entity streamed from Firebase.
 *
 * Firebase structure:
 *  buses/{busId}/
 *      ├── lat (Double)
 *      ├── lng (Double)
 *      ├── speed (Float, meters/second)
 */
data class LiveBus(
    val id: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,

    /**
     * Current speed of the bus in meters per second.
     * Defaults to 0 when unavailable.
     */
    val speed: Float = 0f
)
