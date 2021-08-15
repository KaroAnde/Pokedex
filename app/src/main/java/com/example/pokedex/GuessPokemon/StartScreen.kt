package com.example.pokedex.GuessPokemon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.databinding.StartScreenBinding

class StartScreen : AppCompatActivity() {

    private lateinit var binding : StartScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startGame.setOnClickListener {
            val intent = Intent(this, GuessPokemon::class.java)
            startActivity(intent)
        }
    }
}