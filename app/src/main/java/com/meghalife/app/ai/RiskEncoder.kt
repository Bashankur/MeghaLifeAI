package com.meghalife.app.ai

object RiskEncoder {

    fun weatherToInt(weather: String): Int {
        return when (weather.lowercase()) {
            "clear" -> 0
            "fog" -> 1
            "rain" -> 2
            else -> 0
        }
    }

    fun terrainToInt(terrain: String): Int {
        return when (terrain.lowercase()) {
            "city" -> 0
            "hill" -> 1
            "waterfall" -> 2
            else -> 0
        }
    }
}
