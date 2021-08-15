package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.API.PokemonViewModel
import com.example.pokedex.API.SinglePokemon
import com.example.pokedex.GuessPokemon.StartScreen
import com.example.pokedex.databinding.PokemonListBinding
import com.example.pokedex.databinding.SplashScreenBinding

class SplashScreen : AppCompatActivity () {

    private lateinit var binding : SplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

       /* var pokemonList = viewModel2.singlePokemon
        pokemonList.observe(this) { singlePokemon : SinglePokemon ->
            binding.textView.text = singlePokemon.toString()
        } */

        Handler().postDelayed({

            val intent = Intent(this, StartScreen::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}