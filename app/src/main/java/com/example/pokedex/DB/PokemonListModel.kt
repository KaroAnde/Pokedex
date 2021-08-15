package com.example.pokedex.DB

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.API.PokemonData
import kotlinx.coroutines.launch

class PokemonListModel : ViewModel() {

    private val _pokemonListLiveData : MutableLiveData<List<PokemonData>> = MutableLiveData()
    val pokemonListLiveData : LiveData<List<PokemonData>> = _pokemonListLiveData

    private lateinit var pokemonDao : PokemonDAO

    fun init(context: Context){
        pokemonDao = DataBase.getDatabase(context).getPokemonDAO()
        fetchData()
    }

    fun fetchData(){
        viewModelScope.launch {
           val list = pokemonDao.fetchData()
            _pokemonListLiveData.value = list
        }
    }
}