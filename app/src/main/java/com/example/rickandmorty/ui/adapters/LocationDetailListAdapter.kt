package com.example.rickandmorty.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.repository.data_models.Character

class LocationDetailListAdapter(private val context: Context) : BaseAdapter() {

    private var residentsList: List<Character> = emptyList()

    override fun getCount(): Int = residentsList.size

    override fun getItem(position: Int): Any = residentsList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = convertView ?: LayoutInflater
            .from(parent?.context ?:context )
            .inflate(R.layout.character_item, parent, false)
        val holder: ViewHolder= view.tag as? ViewHolder ?: ViewHolder(view)

        val item = residentsList[position]
        with(holder){
            name.text= item.name
           Glide.with(view).load(item.image).centerCrop().into(imageView)
        }
        return view
    }
    private class ViewHolder(view: View) {
        val name: TextView = view.findViewById(R.id.character_name)
        val imageView : ImageView = view.findViewById(R.id.character_image)
    }
    fun setList(newResidentsList: List<Character>){
        residentsList = newResidentsList
        notifyDataSetChanged()
    }

}
