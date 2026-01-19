package com.meghalife.app.data

data class TravelAdvisory(
    val bestMonths: List<String>,
    val avoidMonths: List<String>,
    val weatherRisk: WeatherRisk,
    val vehicleSuggestions: List<String>,
    val advisoryNote: String
)

data class WeatherRisk(
    val rain: String,
    val landslide: String,
    val fog: String
)
