package com.example.gawbillreycle_webview

import androidx.room.*

/**
 * This is the Dao which contains all the
 * functionalities of this table
 */
@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    fun getAll(): List<Weather>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weather: ArrayList<Weather>)

    @Query("DELETE FROM weather")
    fun deleteAll()
}