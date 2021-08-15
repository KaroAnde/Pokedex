package com.example.pokedex.API

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SinglePokemon(
        val abilities : List<Abilities>,
        val types : List<Types>,
        val sprites : Sprites,
        val id : Int,
        val weight : Int
)
