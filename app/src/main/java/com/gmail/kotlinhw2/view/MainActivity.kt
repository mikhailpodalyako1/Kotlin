package com.gmail.kotlinhw2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.gmail.kotlinhw2.R
import com.gmail.kotlinhw2.databinding.MainActivityBinding

class MainActivity : AppCompatActivity()

        private lateinit var binding: MainActivityBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = MainActivityBinding.inflate(layoutInflater)
    setContentView(binding.root)
    if (savedInstanceState == null) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, MainFragment.newInstance())
            .commitNow()
        }
    }
}