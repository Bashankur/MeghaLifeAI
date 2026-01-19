package com.meghalife.app.repository

import com.google.firebase.database.FirebaseDatabase
import com.meghalife.app.data.DriverRating

class DriverRatingRepository {

    private val db = FirebaseDatabase.getInstance()
        .getReference("driver_ratings")

    fun submitRating(
        driverId: String,
        rating: DriverRating
    ) {
        val id = db.child(driverId).push().key ?: return
        db.child(driverId).child(id).setValue(rating)
    }
}
