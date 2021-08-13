package com.example.pokedex.API

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("pokemon?offset=0&limit=1000")
    suspend fun getAllPokemon(
        //@Query("offset") offset : Int,
        //@Query("limit") limit : Int
    ) : AllPokemonResponse

}