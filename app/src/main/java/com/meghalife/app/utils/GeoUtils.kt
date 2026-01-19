package com.meghalife.app.utils

import android.location.Location

/**
 * Calculates the straight-line (Haversine-based) distance
 * between two geographic coordinates.
 *
 * @param lat1 Latitude of first point
 * @param lng1 Longitude of first point
 * @param lat2 Latitude of second point
 * @param lng2 Longitude of second point
 * @return Distance in meters
 */
fun distanceInMeters(
    lat1: Double,
    lng1: Double,
    lat2: Double,
    lng2: Double
): Float {

    // Validate latitude & longitude ranges
    if (
        lat1 !in -90.0..90.0 ||
        lat2 !in -90.0..90.0 ||
        lng1 !in -180.0..180.0 ||
        lng2 !in -180.0..180.0
    ) {
        return 0f
    }

    val result = FloatArray(1)

    Location.distanceBetween(
        lat1,
        lng1,
        lat2,
        lng2,
        result
    )

    return result.first()
}
