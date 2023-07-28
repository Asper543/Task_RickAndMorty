package com.example.rickandmorty.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rickandmorty.repository.data_models.Character
import com.example.rickandmorty.R

class CharactersRecycleViewAdapter ( private val listener:(Int)->Unit):
    RecyclerView.Adapter<CharactersRecycleViewAdapter.CharacterViewHolder>() {

    private var characterArrayList: ArrayList<Character> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(item)
    }

    override fun getItemCount(): Int = characterArrayList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = characterArrayList[position]
        with(holder){
            name.text = item.name
            this.bind(item,listener)
            Glide.with(image).load(item.image).circleCrop().into(image)
        }
    }
    class CharacterViewHolder(itemView: View) : ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.character_name)
        val image: ImageView = itemView.findViewById(R.id.character_image)
        fun bind(character: Character,onItemClick: (Int) -> Unit) {
            itemView.setOnClickListener { onItemClick(character.characterId) }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setCharacters(characters: ArrayList<Character>){
        characterArrayList = characters
        notifyDataSetChanged()
    }
}