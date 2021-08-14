package com.example.pokedex.API

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SinglePokemon(
        val abilities : List<Abilities>
)
