package com.meghalife.app.data

import androidx.annotation.DrawableRes

/**
 * Represents a Tourist Point of Interest (POI).
 *
 * This model is used across:
 * - Explore Map
 * - Travel Advisory
 * - AI recommendation logic
 * - Transport fusion (which bus / taxi to take)
 */
data class TouristPOI(

    /* ───── Core Info ───── */
    val name: String,
    val lat: Double,
    val lng: Double,
    val category: String,
    val description: String,

    /* ───── Visuals ───── */
    @DrawableRes
    val images: List<Int>,

    /* ───── Transport Fusion ───── */
    val transportInfo: String,
    val transportContacts: List<String>,

    /* ───── AI / Advisory Fields ───── */
    val bestMonths: List<Int>,
    val aiScore: Float = 0f,
    val recommended: Boolean = false
)
