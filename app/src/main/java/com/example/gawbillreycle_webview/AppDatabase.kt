package com.example.gawbillreycle_webview

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Weather::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}