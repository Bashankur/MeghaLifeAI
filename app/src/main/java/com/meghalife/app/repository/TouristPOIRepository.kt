package com.meghalife.app.repository

import android.content.Context
import com.meghalife.app.data.TouristPOI
import com.meghalife.app.data.touristPOIs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Hackathon-safe repository.
 * Static POI data is used for reliability.
 */
class TouristPOIRepository(
    context: Context
) {

    fun getAllPOIs(): Flow<List<TouristPOI>> = flow {
        emit(touristPOIs)
    }

    fun searchPOIs(query: String): Flow<List<TouristPOI>> = flow {
        emit(
            touristPOIs.filter {
                it.name.contains(query, true) ||
                        it.category.contains(query, true)
            }
        )
    }
}
