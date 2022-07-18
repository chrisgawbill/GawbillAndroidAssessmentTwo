package com.example.gawbillreycle_webview

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.recyclerview.widget.RecyclerView

class RecycleAdapter(weatherWeek:ArrayList<Weather>): RecyclerView.Adapter<RecycleAdapter.WeatherViewHolder>() {
    var arrayOfWeather: ArrayList<Weather> = weatherWeek
    var LOG_TAG:String = RecycleAdapter::class.java.simpleName


    /**
     * This creates the row in the recycler view
     * It inflates the the recycle_row layout file
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        var viewInflate: View = LayoutInflater.from(parent.context).inflate(R.layout.recycle_row, parent, false)
        Log.d(LOG_TAG,"Created the Row")
        return WeatherViewHolder(viewInflate)
    }

    /**
     * This binds the data to each view holder for the row
     */
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        var weatherDay:Weather = arrayOfWeather.get(position)
        holder.dayTextView.text = holder.itemView.context.getString(R.string.day_of) + " " + weatherDay.day
        holder.minTempTextView.text = holder.itemView.context.getString(R.string.min_temp) + " " + weatherDay.minTemp.toString() + "F"
        holder.maxTempTextView.text = holder.itemView.context.getString(R.string.max_temp) + " " + weatherDay.maxTemp.toString() + "F"
        Log.d(LOG_TAG,"Binding the data")
        holder.itemView.setOnClickListener(View.OnClickListener {
            rowOnClick(holder.itemView.context, position)
        })

    }

    /**
     * Returns the array size of arrayOfStrings
     */
    override fun getItemCount(): Int {
        Log.d(LOG_TAG,"Returning the array size")
        return arrayOfWeather.size
    }

    /**
     * This is the on click method for each row
     * It uses the passed in position to grab the correct object in the arrayOfWeather array
     * It then uses the passed in context to construct a dialog box that will show additional details
     */
    fun rowOnClick(context: Context, position:Int){
        Log.d(LOG_TAG,"Row was clicked")
        var weatherDay = arrayOfWeather.get(position)
        var day:String = weatherDay.day
        var minTemp:Double = weatherDay.minTemp
        var maxTemp:Double = weatherDay.maxTemp
        var avgTemp:Double = weatherDay.avgTemp
        var totalPrecip:Double = weatherDay.totalPrecip

        var builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.weather_for) + day)
        builder.setMessage(context.getString(R.string.min_temp) + " " +  minTemp.toString() + "F\n" +
                        context.getString(R.string.max_temp) + " " + maxTemp.toString() + "F\n" +
                        context.getString(R.string.avg_temp) + " " + avgTemp.toString() + "F\n" +
                        context.getString(R.string.total_precip) + " " + totalPrecip.toString() + "in")
        builder.setPositiveButton(context.getString(R.string.dialog_okay),
            DialogInterface.OnClickListener { dialog, which ->
            })
        builder.create().show()
    }

    /**
     * This View Holder initializes the variables that holds the references to the text views in each row
     */
    class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var dayTextView = itemView.findViewById<TextView>(R.id.dayTextView)
        var minTempTextView = itemView.findViewById<TextView>(R.id.minTempText)
        var maxTempTextView = itemView.findViewById<TextView>(R.id.maxTempText)
    }
}