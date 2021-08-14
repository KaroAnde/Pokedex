package com.example.pokedex.API

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("pokemon?offset=0&limit=1000")
    suspend fun getAllPokemon(
        //@Query("offset") offset : Int,
        //@Query("limit") limit : Int
    ) : AllPokemonResponse

    @GET("pokemon/{name}")
    suspend fun getSinglePokemon(@Path("name") name : String) : SinglePokemon

}