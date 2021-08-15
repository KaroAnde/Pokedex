package com.example.pokedex

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pokedex.API.Abilities
import com.example.pokedex.API.PokemonData
import com.example.pokedex.API.PokemonViewModel
import com.example.pokedex.API.SinglePokemon
import com.example.pokedex.databinding.SinglePokemonActBinding
import java.util.*

class SinglePokemonAct : AppCompatActivity() {

    private lateinit var binding : SinglePokemonActBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SinglePokemonActBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name : String = intent.getStringExtra("pokemonName").toString().toLowerCase(Locale.ROOT)
         val viewModel : PokemonViewModel by viewModels {
            PokemonViewModel.Factory(name)
        }

        var pokemonList = viewModel.singlePokemon


        pokemonList.observe(this) { singlePokemon : SinglePokemon ->

            binding.pokemonName.text = name.capitalize()
            binding.pokemonWeight.text = singlePokemon.weight.toString()
            binding.pokemonNumber.text = singlePokemon.id.toString()
            binding.abilityOne.text = singlePokemon.abilities.map { it.ability.name}.toString()
            binding.pokemonType.text = singlePokemon.types.map{ it.type.name }.toString()

            Glide.with(this).load(singlePokemon.sprites.front_default).fitCenter().into(binding.pokemonImg)

        }

    }
}