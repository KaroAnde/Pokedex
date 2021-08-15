package com.example.pokedex.DB

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.pokedex.API.AllPokemonResponse
import com.example.pokedex.API.PokemonData

const val DATABASE_NAME : String = "pokemons"

@Database(entities = [PokemonData::class], version = 1)
abstract class DataBase : RoomDatabase(){
    abstract fun getPokemonDAO() : PokemonDAO

    companion object {

        var db : DataBase? = null

        fun getDatabase(context : Context) : DataBase {
            //Singleton - use this object every single time. Create only once because its expensive
            //Should use thread locking
            val newDB = db?: Room.databaseBuilder(context,
                DataBase::class.java, DATABASE_NAME)
                .build()

            return newDB.also {
                db = it
            }
        }
    }
}