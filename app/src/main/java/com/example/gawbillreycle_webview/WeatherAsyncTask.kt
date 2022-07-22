package com.example.gawbillreycle_webview

import android.content.ContentValues
import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Double.parseDouble
import java.lang.ref.WeakReference


class WeatherAsyncTask(view:RecyclerView, data:AppDatabase):AsyncTask<String, Void, String>() {
    var LOG_TAG:String = WeatherAsyncTask::class.java.simpleName
    var recycler:WeakReference<RecyclerView> = WeakReference(view)
    var db:WeakReference<AppDatabase> = WeakReference(data)
    /**
     * This method calls NetworkUtils to get the api JSON data
     */
    override fun doInBackground(vararg p0: String?): String {
        var network:NetworkUtils = NetworkUtils()
        Log.d(LOG_TAG, "Doing background task")
        return network.getWeatherInfo().toString()
    }

    /**
     * This method turns the JSON string into a Weather JSON object
     * It then stores that Weather object in an array
     * At the end of the while loop an adapter is created and passed into the Weak Reference Recycler View
     * The array of weather is passed into that adapter
     */
    override fun onPostExecute(result: String?) {
        try{
            var jsonObject: JSONObject = JSONObject(result)
            var jsonForecast: JSONObject = jsonObject.getJSONObject("forecast")
            var jsonForecastDay:JSONArray = jsonForecast.getJSONArray("forecastday")
            var i:Int = 0
            var weatherArray:ArrayList<Weather> = ArrayList()
            while(i < jsonForecastDay.length()){
                Log.d(LOG_TAG,jsonForecastDay.get(i).toString())
                var day:JSONObject = jsonForecastDay.getJSONObject(i)
                var date:String = day.getString("date")
                var dayDetails:JSONObject = day.getJSONObject("day")
                var maxTemp:Double = parseDouble(dayDetails.getString("maxtemp_f").toString())
                var minTemp:Double = parseDouble(dayDetails.getString("mintemp_f").toString())
                var avgTemp:Double = parseDouble(dayDetails.getString("avgtemp_f").toString())
                var totalPrecip:Double = parseDouble(dayDetails.getString("totalprecip_in").toString())

                var weather:Weather = Weather(0,date, maxTemp, minTemp, avgTemp, totalPrecip)
                weatherArray.add(weather)
                i++
            }
             putIntoRoom(weatherArray)


        }catch(ex:Exception){
            Log.d(LOG_TAG,"There was an error")
            ex.printStackTrace()
        }
    }

    /**
     * Here we are putting the data gotten from the JSON into room db
     * We are putting the data in as a list because in the WeatherDao.insertAll()
     * it will take that list and put each item into room DB
     */
    fun putIntoRoom(weatherArray:ArrayList<Weather>){
        AppExecutors.getInstance().diskIO().execute {
            val weatherDao = db.get()?.weatherDao()
            weatherDao?.deleteAll()
            weatherDao?.insertAll(weatherArray)

            Log.d(ContentValues.TAG, "Submitted weather")
        }
    }
}