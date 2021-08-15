package com.example.pokedex.API

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//(private val name:String
class PokemonViewModel(val name: String) : ViewModel() {

    val retrofitService = API.retrofitService

    private val _allPokemons = MutableLiveData<AllPokemonResponse>()
    val allPokemons : LiveData<AllPokemonResponse> get() = _allPokemons

    private val _singlePokemon = MutableLiveData<SinglePokemon>()
    val singlePokemon : LiveData<SinglePokemon> get () = _singlePokemon

    init {
        viewModelScope.launch(Dispatchers.IO){
            val everyPokemon = retrofitService.getAllPokemon()
            _allPokemons.postValue(everyPokemon)
        }

        viewModelScope.launch(Dispatchers.IO) {
            val singlePokemon = retrofitService.getSinglePokemon(name)
            _singlePokemon.postValue(singlePokemon)
        }
    }

   class Factory(private val name : String) : ViewModelProvider.Factory{

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create (modelClass: Class<T>) : T {
            return PokemonViewModel(name) as T
        }
    }
}

