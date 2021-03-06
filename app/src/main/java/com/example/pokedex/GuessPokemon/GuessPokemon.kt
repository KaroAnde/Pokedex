package com.example.pokedex.GuessPokemon

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.pokedex.API.PokemonData
import com.example.pokedex.DB.DbViewModel
import com.example.pokedex.DB.PokemonListModel
import com.example.pokedex.databinding.GuessPokemonBinding



class GuessPokemon : AppCompatActivity() {

    private val gameManager = GameManager()
    private lateinit var binding : GuessPokemonBinding
    val dbViewModel : PokemonListModel by viewModels()
    lateinit var pokemonFilterList: MutableList<PokemonData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GuessPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbViewModel.init(this)
        dbViewModel.pokemonListLiveData.observe(this) { DBList ->
            pokemonFilterList = DBList as MutableList<PokemonData>
            var test = pokemonFilterList.map { it.name }
            Log.d("Fetch", test[100])
            GameConstants.words = test
        }


        binding.newGame.setOnClickListener {
            startNewGame()
        }
        val gameState = gameManager.startNewGame()
        updateUI(gameState)

        binding.lettersLayout.children.forEach { letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener {
                    val gameState = gameManager.play((letterView).text[0])
                    updateUI(gameState)
                    letterView.visibility = View.GONE
                }
            }
        }

    }

    private fun updateUI(gameState : GameState) {
        when(gameState) {
            is GameState.Lost -> showGameLost(gameState.wordToGuess)
            is GameState.Running -> {
                binding.wordTextView.text = gameState.underscoreWord
                binding.lettersUsedTextView.text = "Letters used: ${gameState.lettersUsed}"
                //drawable
            }
            is GameState.Won -> showGameWon(gameState.wordToGuess)
        }
    }

    private fun showGameLost (wordToGuess: String) {
        binding.wordTextView.text = wordToGuess
        binding.gameLostTextView.visibility = View.VISIBLE
        //drawable imageview
        binding.lettersLayout.visibility = View.GONE
    }

    private fun showGameWon (wordToGuess: String) {
        binding.wordTextView.text = wordToGuess
        binding.gameWonTextView.visibility = View.VISIBLE
        binding.lettersLayout.visibility = View.GONE
    }

    private fun startNewGame(){
        binding.gameLostTextView.visibility = View.GONE
        binding.gameWonTextView.visibility = View.GONE
        val gameState = gameManager.startNewGame()
        binding.lettersLayout.visibility = View.VISIBLE
        binding.lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        updateUI(gameState)
    }


}