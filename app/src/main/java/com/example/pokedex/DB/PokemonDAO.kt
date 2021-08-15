package com.example.pokedex.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pokedex.API.PokemonData


@Dao
interface PokemonDAO {

    @Query("SELECT * FROM Pokemons")
    suspend fun fetchData () : List<PokemonData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: PokemonData)

    @Update
    suspend fun update (pokemons: PokemonData)
}