package com.example.gawbillreycle_webview

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Array.get

/**
 * This is the Weather object which holds detail for weather for each fetched day
 * This is a data class that is an Entity which tells Room that this is a table
 * Auto gens primary key
 */
@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "maxTemp") val maxTemp: Double,
    @ColumnInfo(name = "minTemp") val minTemp:Double,
    @ColumnInfo(name = "avgTemp") val avgTemp:Double,
    @ColumnInfo(name = "totalPrecip") val totalPrecip:Double
)