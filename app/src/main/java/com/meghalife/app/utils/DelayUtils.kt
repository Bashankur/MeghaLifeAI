package com.meghalife.app.utils

/**
 * Represents the running status of a vehicle
 * compared to its expected arrival time.
 */
enum class DelayStatus {
    EARLY,
    ON_TIME,
    DELAYED
}

/**
 * Calculates whether a vehicle is early, on time,
 * or delayed based on ETA comparison.
 *
 * @param expectedEtaMin Expected ETA in minutes
 * @param actualEtaMin Actual ETA in minutes
 * @return [DelayStatus] indicating schedule performance
 */
fun calculateDelayStatus(
    expectedEtaMin: Int,
    actualEtaMin: Int
): DelayStatus {

    // Guard against invalid inputs
    if (expectedEtaMin <= 0 || actualEtaMin <= 0) {
        return DelayStatus.ON_TIME
    }

    val toleranceMinutes = 2

    return when {
        actualEtaMin <= expectedEtaMin - toleranceMinutes ->
            DelayStatus.EARLY

        actualEtaMin >= expectedEtaMin + toleranceMinutes ->
            DelayStatus.DELAYED

        else ->
            DelayStatus.ON_TIME
    }
}
