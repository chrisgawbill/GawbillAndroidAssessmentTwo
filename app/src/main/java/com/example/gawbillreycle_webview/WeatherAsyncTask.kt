package com.example.gawbillreycle_webview

import android.os.AsyncTask
import android.util.Log
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt
import java.lang.ref.WeakReference
import java.lang.reflect.Array.getInt

class WeatherAsyncTask(view:RecyclerView):AsyncTask<String, Void, String>() {
    var LOG_TAG:String = WeatherAsyncTask::class.java.simpleName
    var recycler:WeakReference<RecyclerView> = WeakReference(view)
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

                var weather:Weather = Weather(date, maxTemp, minTemp, avgTemp, totalPrecip)
                weatherArray.add(weather)
                i++
            }
            Log.d(LOG_TAG,"Got the data")
            var adapter:RecycleAdapter = RecycleAdapter(weatherArray)
            recycler.get()?.adapter = adapter


        }catch(ex:Exception){
            Log.d(LOG_TAG,"There was an error")
            ex.printStackTrace()
        }
    }
}