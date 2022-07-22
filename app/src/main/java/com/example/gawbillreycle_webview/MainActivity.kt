package com.example.gawbillreycle_webview

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    lateinit var dataRecyclerView:RecyclerView
    lateinit var getDataButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        dataRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        dataRecyclerView.layoutManager = layoutManager
        lateinit var adapter:RecycleAdapter
        WeatherAsyncTask(dataRecyclerView,db).execute("Some")
        getDataButton = findViewById(R.id.getData)
        getDataButton.setOnClickListener(View.OnClickListener {
            getData()
        })
    }
    fun getData(){

        AppExecutors.getInstance().diskIO().execute {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            val weatherDao = db.weatherDao()
            var roomWeatherArray = weatherDao?.getAll() as List<Weather>
            Log.d(ContentValues.TAG, roomWeatherArray.toString())

            this@MainActivity.runOnUiThread(Runnable {
                var adapter:RecycleAdapter = RecycleAdapter(roomWeatherArray)
                dataRecyclerView.adapter = adapter
            })
        }
    }
}