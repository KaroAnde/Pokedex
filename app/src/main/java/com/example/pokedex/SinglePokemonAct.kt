package com.example.pokedex

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.API.Abilities
import com.example.pokedex.API.PokemonData
import com.example.pokedex.API.PokemonViewModel
import com.example.pokedex.API.SinglePokemon
import com.example.pokedex.databinding.SinglePokemonActBinding

class SinglePokemonAct : AppCompatActivity() {

    private lateinit var binding : SinglePokemonActBinding
     private val viewModel : PokemonViewModel by viewModels {
        PokemonViewModel.Factory("charmander")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SinglePokemonActBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pokemonList = viewModel.singlePokemon
        pokemonList.observe(this) { singlePokemon : SinglePokemon ->

            binding.abilityOne.text = singlePokemon.abilities.map { it.ability.name}.toString()


        }

    }
}