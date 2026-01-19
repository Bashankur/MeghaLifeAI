package com.meghalife.app.data

object TravelAdvisorEngine {

    fun getDefaultAdvisory(): TravelAdvisory {

        return TravelAdvisory(
            bestMonths = listOf(
                "October", "November", "December",
                "January", "February", "March", "April"
            ),

            avoidMonths = listOf(
                "June", "July", "August", "September"
            ),

            weatherRisk = WeatherRisk(
                rain = "Heavy rainfall during monsoon season",
                landslide = "Moderate to high risk on hilly roads",
                fog = "Common in winter mornings (Dec–Jan)"
            ),

            vehicleSuggestions = listOf(
                "Local taxi (recommended)",
                "Shared sumo",
                "State bus (daytime travel)",
                "Avoid two-wheelers during monsoon"
            ),

            advisoryNote =
                "This advisory is based on seasonal weather patterns, " +
                        "road safety conditions, and local transport availability."
        )
    }
}
