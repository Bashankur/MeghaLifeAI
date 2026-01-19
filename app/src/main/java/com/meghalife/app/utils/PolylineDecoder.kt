package com.meghalife.app.utils

import com.google.android.gms.maps.model.LatLng

/**
 * Decodes an encoded polyline string into a list of [LatLng] points.
 *
 * This implementation follows Google's Polyline Algorithm:
 * https://developers.google.com/maps/documentation/utilities/polylinealgorithm
 *
 * @param encoded Encoded polyline string
 * @return List of decoded [LatLng] points
 */
fun decodePolyline(encoded: String): List<LatLng> {

    if (encoded.isBlank()) return emptyList()

    val coordinates = mutableListOf<LatLng>()

    var index = 0
    val length = encoded.length

    var latitude = 0
    var longitude = 0

    while (index < length) {

        // Decode latitude
        val deltaLat = decodeNextValue(encoded, length) { index = it }
        latitude += deltaLat

        // Decode longitude
        val deltaLng = decodeNextValue(encoded, length) { index = it }
        longitude += deltaLng

        coordinates.add(
            LatLng(
                latitude / 1E5,
                longitude / 1E5
            )
        )
    }

    return coordinates
}

/**
 * Decodes the next signed value from the encoded polyline string.
 */
private inline fun decodeNextValue(
    encoded: String,
    length: Int,
    updateIndex: (Int) -> Unit
): Int {

    var result = 0
    var shift = 0
    var index = encodedIndex

    var byte: Int

    do {
        if (index >= length) break

        byte = encoded[index++].code - 63
        result = result or ((byte and 0x1F) shl shift)
        shift += 5

    } while (byte >= 0x20)

    updateIndex(index)

    return if (result and 1 != 0)
        (result shr 1).inv()
    else
        result shr 1
}

/**
 * Internal pointer used during polyline decoding.
 * Kept package-private to avoid global state leaks.
 */
private var encodedIndex = 0
