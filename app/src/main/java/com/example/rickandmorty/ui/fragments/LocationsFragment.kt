package com.example.rickandmorty.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.LOCATION_ID
import com.example.rickandmorty.R
import com.example.rickandmorty.RickAndMortyApplication
import com.example.rickandmorty.ui.adapters.LocationRecyclerViewAdapter
import com.example.rickandmorty.view_models.RickAndMortyViewModel
import javax.inject.Inject

class LocationsFragment: Fragment() {
    @Inject
    lateinit var model: RickAndMortyViewModel
    override fun onAttach(context: Context) {
        (context.applicationContext as RickAndMortyApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.loadLocations()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = view.findViewById(R.id.location_recycler_view)
        recycler.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false)

        val locationsAdapter = LocationRecyclerViewAdapter{
            clickListener(it)
        }
        recycler.adapter = locationsAdapter
        model.locationsData.observe(this) { data ->
            locationsAdapter.setLocations(data)
        }
    }
    private fun clickListener(id: Int) {
        val bundle = Bundle().apply { putInt(LOCATION_ID, id) }
        findNavController().navigate(R.id.to_location_details,bundle)
    }
}