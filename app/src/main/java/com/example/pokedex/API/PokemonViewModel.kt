package com.example.pokedex.API

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    val retrofitService = API.retrofitService

    private val _allPokemons = MutableLiveData<AllPokemonResponse>()
    val allPokemons : LiveData<AllPokemonResponse> get() = _allPokemons

    init {
        viewModelScope.launch(Dispatchers.IO){
            val everyPokemon = retrofitService.getAllPokemon()
            _allPokemons.postValue(everyPokemon)
        }
    }

}