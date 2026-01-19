package com.meghalife.app.data

import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Repository responsible for listening to live bus location updates
 * from Firebase Realtime Database.
 *
 * Data path:
 *  buses/{busId}/lat
 *  buses/{busId}/lng
 *  buses/{busId}/speed
 */
class BusRepository {

    private val databaseRef: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("buses")

    private val _buses = MutableStateFlow<List<LiveBus>>(emptyList())
    val buses: StateFlow<List<LiveBus>> = _buses

    private val busListener = object : ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {

            val updatedBuses = mutableListOf<LiveBus>()

            snapshot.children.forEach { busSnapshot ->

                val latitude =
                    busSnapshot.child("lat")
                        .getValue(Double::class.java)

                val longitude =
                    busSnapshot.child("lng")
                        .getValue(Double::class.java)

                val speed =
                    busSnapshot.child("speed")
                        .getValue(Float::class.java) ?: 0f

                if (latitude != null && longitude != null) {
                    updatedBuses.add(
                        LiveBus(
                            id = busSnapshot.key.orEmpty(),
                            lat = latitude,
                            lng = longitude,
                            speed = speed
                        )
                    )
                }
            }

            _buses.value = updatedBuses
        }

        override fun onCancelled(error: DatabaseError) {
            // Optional: add logging or analytics
        }
    }

    init {
        databaseRef.addValueEventListener(busListener)
    }
}
