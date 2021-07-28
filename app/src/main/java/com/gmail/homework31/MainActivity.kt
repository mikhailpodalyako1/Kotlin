package com.gmail.homework31

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.gmail.homework31.repository.getRepository

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerWeather: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerWeather = findViewById(R.id.recyclerWeather)
        val adapter = WeatherAdapter.getInstanse(getRepository())
        recyclerWeather.adapter = adapter

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity, "Button is clicked!", Toast.LENGTH_SHORT).show()

            }

        })
    }
}