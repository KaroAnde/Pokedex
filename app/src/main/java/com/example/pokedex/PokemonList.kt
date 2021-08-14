package com.example.pokedex

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
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

        pokemonList.observe(this) { listPokemons: AllPokemonResponse ->
            var adapter = RecyclerAdapter(listPokemons.results)
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


    private fun performSearch(){

    }

   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search_bar).actionView as SearchView).apply{
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)

        }

    }*/

}