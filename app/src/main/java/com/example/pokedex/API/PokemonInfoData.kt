package com.example.pokedex.API

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfoData(
  var name : List<PokemonData>
)
