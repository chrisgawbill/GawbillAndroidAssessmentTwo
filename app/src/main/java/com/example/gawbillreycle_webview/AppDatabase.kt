package com.example.gawbillreycle_webview

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * This serves as the database class
 * Which is the main access point into our database
 */
@Database(entities = [Weather::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}