package com.example.realnavermoviefinder.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.realnavermoviefinder.R
import com.example.realnavermoviefinder.databinding.ActivityMainBinding
import com.example.realnavermoviefinder.view.result.ResultActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("movieTitle", binding.movieTitle.text.toString())
            startActivity(intent)
        }
    }
}