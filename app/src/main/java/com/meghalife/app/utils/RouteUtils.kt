package com.meghalife.app.utils

import com.google.android.gms.maps.model.LatLng

/**
 * Calculates the remaining distance (in meters) along a route
 * from the current position to the end of the polyline.
 *
 * The function:
 * 1. Finds the nearest route point within a threshold
 * 2. Sums distances from that point to the route end
 *
 * @param currentLat Current latitude
 * @param currentLng Current longitude
 * @param route Polyline route points
 * @return Remaining distance in meters
 */
fun remainingRouteDistance(
    currentLat: Double,
    currentLng: Double,
    route: List<LatLng>
): Float {

    if (route.size < 2) return 0f

    var totalDistance = 0f
    var hasReachedRoute = false

    val snapThresholdMeters = 30f

    for (index in route.indices) {

        val point = route[index]

        if (!hasReachedRoute) {
            // Find the nearest route point to current position
            val distanceToPoint = distanceInMeters(
                currentLat,
                currentLng,
                point.latitude,
                point.longitude
            )

            if (distanceToPoint <= snapThresholdMeters) {
                hasReachedRoute = true
            }

            continue
        }

        // Sum distance between consecutive route points
        if (index < route.lastIndex) {
            totalDistance += distanceInMeters(
                route[index].latitude,
                route[index].longitude,
                route[index + 1].latitude,
                route[index + 1].longitude
            )
        }
    }

    return totalDistance
}
