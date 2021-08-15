package com.example.pokedex.DB

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.API.PokemonData
import kotlinx.coroutines.launch

class DbViewModel : ViewModel() {

    private lateinit var pokemonDao : PokemonDAO

    fun init(context : Context) {
       pokemonDao = DataBase.getDatabase(context).getPokemonDAO()
    }


     fun save(name : String, url : String?) {
        viewModelScope.launch {
            if(name.isNullOrEmpty() || url.isNullOrEmpty()){
                return@launch
            }
            pokemonDao.insertAll(PokemonData(name = name, url = url))
            Log.d("Save","insert happened")
        }
    }

}