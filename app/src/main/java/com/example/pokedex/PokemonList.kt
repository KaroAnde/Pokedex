package com.example.pokedex

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.API.AllPokemonResponse
import com.example.pokedex.API.PokemonViewModel
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.databinding.PokemonListBinding

class PokemonList : AppCompatActivity (){

    private lateinit var binding : PokemonListBinding
    val viewModel : PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pokemonList = viewModel.allPokemons

       /* pokemonList.observe(this) { allPokemons : AllPokemonResponse ->
            binding.test.text = allPokemons.toString()
        }*/

        pokemonList.observe(this) { listPokemons : AllPokemonResponse ->
            var adapter = RecyclerAdapter(listPokemons.results)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.setHasFixedSize(true)
        }
    }
}