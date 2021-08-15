package com.example.pokedex

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.API.AllPokemonResponse
import com.example.pokedex.API.PokemonData
import com.example.pokedex.API.PokemonViewModel
import com.example.pokedex.API.SinglePokemon
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.databinding.PokemonListBinding

class PokemonList : AppCompatActivity (){

    private lateinit var binding : PokemonListBinding
    val viewModel : PokemonViewModel by viewModels() {
        PokemonViewModel.Factory("pikachu")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pokemonList = viewModel.allPokemons


        pokemonList.observe(this) { listPokemons: AllPokemonResponse ->
            var adapter = RecyclerAdapter(listPokemons.results) { position -> onListItemClick(position) }
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.setHasFixedSize(true)



            binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    var adptfilter = adapter.getFilter()
                    adptfilter.filter(newText)
                    return false
                }
            })
        }
    }



    private fun onListItemClick(position : String) {
        Toast.makeText(this, position, Toast.LENGTH_SHORT).show()


        val nextScreen = Intent(this@PokemonList, SinglePokemonAct::class.java)
        nextScreen.putExtra("pokemonName", position)
        startActivity(nextScreen)
    }
   /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search_bar).actionView as SearchView).apply{
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }*/



}