package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.pokedex.API.AllPokemonResponse
import com.example.pokedex.API.PokemonViewModel
import com.example.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    val viewModel : PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pokemonList = viewModel.allPokemons

        pokemonList.observe(this) { allPokemons : AllPokemonResponse ->
            binding.test.text = allPokemons.toString()
        }
    }
}