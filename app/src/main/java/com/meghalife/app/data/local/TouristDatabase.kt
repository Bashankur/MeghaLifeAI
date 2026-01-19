package com.meghalife.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TouristPOIEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TouristDatabase : RoomDatabase() {

    abstract fun poiDao(): TouristPOIDao

    companion object {

        @Volatile
        private var INSTANCE: TouristDatabase? = null

        fun get(context: Context): TouristDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TouristDatabase::class.java,
                    "tourist.db"
                ).build().also { INSTANCE = it }
            }
    }
}
