package com.example.rickandmorty.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rickandmorty.LOCATION_ID
import com.example.rickandmorty.R
import com.example.rickandmorty.RickAndMortyApplication
import com.example.rickandmorty.ui.adapters.LocationDetailListAdapter
import com.example.rickandmorty.view_models.LocationDetailViewModel
import javax.inject.Inject

class LocationDetailFragment:Fragment() {
    @Inject
    lateinit var model:LocationDetailViewModel
    override fun onAttach(context: Context) {
        (context.applicationContext as RickAndMortyApplication).appComponent.inject(this)
        model.cleared()
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(LOCATION_ID)?.let {
            model.loadLocation(it)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_details_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name: TextView = view.findViewById(R.id.location_detail_name)
        val type: TextView = view.findViewById(R.id.location_detail_type)
        val dimension: TextView = view.findViewById(R.id.location_detail_dimension)
        val listView: ListView = view.findViewById(R.id.residents_list_view)
        val adapterList = LocationDetailListAdapter(requireContext())

        listView.adapter = adapterList
        model.charactersData.observe(this) {
             if (it!=null)adapterList.setList(it)
        }
        model.locationDetailData.observe(this){
            it?.let {
                name.text= it.locationName
                type.text= it.locationType
                dimension.text = it.locationDimension
            }
        }
    }
}