package com.example.pokedex.API

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonData(

    val name : String,
    val url: String
)
