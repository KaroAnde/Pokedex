package com.example.pokedex

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pokedex.API.*
import com.example.pokedex.databinding.SinglePokemonActBinding
import java.util.*

class SinglePokemonAct : AppCompatActivity() {

    private lateinit var binding : SinglePokemonActBinding
    private lateinit var abilitiesList: List<Abilities>
    private lateinit var typesList : List <Types>

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

            abilitiesList = singlePokemon.abilities
            typesList = singlePokemon.types
            var test = abilitiesList.map { it.ability.name }
            var types = typesList.map {it.type.name}


            binding.pokemonName.text = name.capitalize()
            binding.pokemonWeight.text = singlePokemon.weight.toString()
            binding.pokemonNumber.text = singlePokemon.id.toString()
            binding.abilityTwo.text = test[1].capitalize()
            binding.abilityOne.text = test[0].capitalize()
            binding.pokemonType.text = types[0]



            Glide.with(this).load(singlePokemon.sprites.front_default).fitCenter().into(binding.pokemonImg)

        }

    }
}