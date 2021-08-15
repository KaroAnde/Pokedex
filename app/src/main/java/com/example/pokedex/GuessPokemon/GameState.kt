package com.example.pokedex.GuessPokemon

sealed class GameState {

    //Map out state
    class Running(val lettersUsed : String, val underscoreWord : String) : GameState() //drawable
    class Lost(val wordToGuess : String) : GameState()
    class Won(val wordToGuess : String) : GameState()

}