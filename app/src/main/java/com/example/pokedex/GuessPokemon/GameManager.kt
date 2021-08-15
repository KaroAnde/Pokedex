package com.example.pokedex.GuessPokemon

import kotlin.random.Random

class GameManager {

    private var lettersUsed : String = ""
    private lateinit var underscoreWord : String
    private lateinit var wordToGuess : String
    private val maxNumberOfTries = 7
    private var currentTries = 0
    //private var drawable: Int = R.drawable.image


    //Resets values
    fun startNewGame() : GameState {
        lettersUsed = ""
        currentTries = 0
        //drawable = R.drawable.standardImage
        val randomIndex = Random.nextInt(0, GameConstants.words.size)
        wordToGuess = GameConstants.words[randomIndex]
        generateUnderscores(wordToGuess)
        return getGameState()
    }

    fun generateUnderscores (word : String) {
        val stringBuilder = StringBuilder()
        word.forEach { char ->
            //Kan fjerne denne fordi jeg bare har ting med 1 ord tror jeg
            if(char == '/'){
                stringBuilder.append('/')
            } else {
                stringBuilder.append("_")
            }
        }
        underscoreWord = stringBuilder.toString()
    }

    fun play(letter : Char) : GameState {
        if(lettersUsed.contains(letter)) {
            return GameState.Running(lettersUsed,underscoreWord) //og drawable
        }

        lettersUsed += "$letter"
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed { index, char ->
            if(char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord
        indexes.forEach { index ->
            val stringBuilder = java.lang.StringBuilder(finalUnderscoreWord).also {
                it.setCharAt(index, letter) }
            finalUnderscoreWord = stringBuilder.toString()
        }

        if(indexes.isEmpty()) {
            currentTries++
        }

        underscoreWord = finalUnderscoreWord
        return  getGameState()
    }

    private fun getGameState() : GameState {
        if (underscoreWord.equals(wordToGuess, true)){
            return GameState.Won(wordToGuess)
        }

        if (currentTries == maxNumberOfTries) {
            return GameState.Lost(wordToGuess)
        }

        //drawable = getHangmanDrawable()
        return GameState.Running(lettersUsed, underscoreWord) //og drawable
    }

}
