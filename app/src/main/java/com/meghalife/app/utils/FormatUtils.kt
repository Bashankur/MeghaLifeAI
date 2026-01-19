package com.meghalife.app.utils

import java.util.Locale

/**
 * Formats a Float value to the specified number of decimal places.
 *
 * Example:
 *  12.3456f.format(2) -> "12.35"
 *
 * @param digits Number of decimal places
 * @return Formatted string representation
 */
fun Float.format(digits: Int): String {

    // Guard against invalid precision
    val safeDigits = digits.coerceIn(0, 6)

    return String.format(
        Locale.US,
        "%.${safeDigits}f",
        this
    )
}
