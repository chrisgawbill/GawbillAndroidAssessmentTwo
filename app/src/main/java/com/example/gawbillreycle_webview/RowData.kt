package com.example.gawbillreycle_webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class RowData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var data:String = intent.getStringExtra("string-value").toString()
        Toast.makeText(this,data, Toast.LENGTH_SHORT).show()
    }
}