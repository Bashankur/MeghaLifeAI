package com.meghalife.app.data

data class DriverRating(
    val driverName: String,
    val rating: Int,        // 1 to 5
    val comment: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
