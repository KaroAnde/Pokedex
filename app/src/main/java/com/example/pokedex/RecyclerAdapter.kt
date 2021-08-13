package com.example.pokedex

import android.app.Activity
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.API.PokemonData
import java.util.*

class RecyclerAdapter (private var pokemonList : List<PokemonData>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    lateinit var url : String


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //TODO:Haha
        val currentItem = pokemonList[position]
        val activity = holder.itemView.context as Activity
        val stringUrl = currentItem.url
        if(stringUrl.length <= 36){
            val substring = stringUrl.subSequence(25,36)
            val digits = substring.filter{it.isDigit()}
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${digits}.png"
        } else{
            val substring = stringUrl.subSequence(25,37)
            val digits = substring.filter{it.isDigit()}
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${digits}.png"
        }



        holder.itemName.text = currentItem.name.capitalize(Locale.ROOT)
        Glide.with(activity).load(url).fitCenter().into(holder.itemSymbol)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val itemName : TextView = itemView.findViewById(R.id.name)
        val itemSymbol : ImageView = itemView.findViewById(R.id.symbol)

    }


}