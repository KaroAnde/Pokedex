package com.example.pokedex.API

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName="pokemons")
data class PokemonData(

    @PrimaryKey
    @ColumnInfo(name="name")
    val name : String,
    @ColumnInfo(name="url")
    val url: String
)
