package com.meghalife.app.network

data class DirectionsResponse(
    val routes: List<Route>
)

data class Route(
    val overview_polyline: OverviewPolyline,
    val legs: List<Leg>
)

data class OverviewPolyline(
    val points: String
)

data class Leg(
    val distance: Distance,
    val duration: Duration
)

data class Distance(
    val value: Int // meters
)

data class Duration(
    val value: Int // seconds
)
