package com.meghalife.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TouristPOIDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pois: List<TouristPOIEntity>)

    @Query("SELECT * FROM tourist_pois")
    fun getAll(): Flow<List<TouristPOIEntity>>

    @Query("""
        SELECT * FROM tourist_pois 
        WHERE name LIKE '%' || :query || '%'
    """)
    fun search(query: String): Flow<List<TouristPOIEntity>>
}

