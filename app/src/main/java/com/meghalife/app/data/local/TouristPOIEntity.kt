package com.meghalife.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tourist_pois")
data class TouristPOIEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val lat: Double,
    val lng: Double,
    val category: String,
    val description: String,
    val bestTime: String,
    val weatherTip: String
)

