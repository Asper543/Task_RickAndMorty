package com.example.rickandmorty.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.repository.data_models.LocationDescription

class LocationRecyclerViewAdapter ( private val listener:(Int)->Unit):
    RecyclerView.Adapter<LocationRecyclerViewAdapter.LocationViewHolder>() {

    private var locationsArrayList: ArrayList<LocationDescription> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        return LocationViewHolder(item)    }

    override fun getItemCount(): Int = locationsArrayList.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = locationsArrayList[position]
        with(holder){
            name.text = item.locationName
            this.bind(item,listener)
        }
    }
    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.location_name)
        fun bind(location: LocationDescription, onItemClick: (Int) -> Unit) {
            itemView.setOnClickListener { onItemClick(location.locationId) }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setLocations(locations:ArrayList<LocationDescription>){
        locationsArrayList = locations
        notifyDataSetChanged()
    }
}