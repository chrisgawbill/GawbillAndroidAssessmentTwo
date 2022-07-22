package com.example.gawbillreycle_webview

import androidx.room.*

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    fun getAll(): List<Weather>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weather: ArrayList<Weather>)
}