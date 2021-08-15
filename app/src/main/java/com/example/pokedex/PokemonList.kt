package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.API.AllPokemonResponse
import com.example.pokedex.API.PokemonData
import com.example.pokedex.API.PokemonViewModel
import com.example.pokedex.DB.DbViewModel
import com.example.pokedex.GuessPokemon.GameManager
import com.example.pokedex.databinding.PokemonListBinding

class PokemonList : AppCompatActivity (){

    private lateinit var binding : PokemonListBinding
    val viewModel : PokemonViewModel by viewModels() {
        PokemonViewModel.Factory("pikachu")
    }
    val dbViewModel : DbViewModel by viewModels()
    private lateinit var pokemonFilterList: MutableList<PokemonData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var pokemonList = viewModel.allPokemons
        dbViewModel.init(this)

        pokemonList.observe(this) { listPokemons: AllPokemonResponse ->
            var adapter = RecyclerAdapter(listPokemons.results) { position -> onListItemClick(position) }
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.setHasFixedSize(true)

            pokemonFilterList = listPokemons.results

            //Log.d("test",test.toString())
            var name = pokemonFilterList.map { it.name }


            for (i in name.indices){
                dbViewModel.save(name[i],"noURL")
            }

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