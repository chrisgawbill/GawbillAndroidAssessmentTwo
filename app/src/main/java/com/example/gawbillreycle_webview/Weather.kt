package com.example.gawbillreycle_webview

import java.lang.reflect.Array.get

/**
 * This is the Weather object which holds detail for weather for each fetched day
 * There is a getter method for each variable so it can be fetched when displaying data in recycler view
 */
class Weather(day:String, maxTemp:Double, minTemp:Double, avgTemp:Double, totalPrecip:Double) {
    val day:String = day
        get(){
            return field
        }
    val maxTemp:Double = maxTemp
        get(){
            return field
        }
    val minTemp:Double = minTemp
        get(){
            return field
        }
    val avgTemp:Double = avgTemp
        get(){
            return field
        }
    val totalPrecip:Double = totalPrecip
        get(){
            return field
        }
}