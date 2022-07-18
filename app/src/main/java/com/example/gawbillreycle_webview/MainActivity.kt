package com.example.gawbillreycle_webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var dataRecyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        dataRecyclerView.layoutManager = layoutManager
        lateinit var adapter:RecycleAdapter
        WeatherAsyncTask(dataRecyclerView).execute("Some")
    }
}